package com.spiny.spiny_demo.Controller;

import com.spiny.spiny_demo.CarStatus;
import com.spiny.spiny_demo.Exception.ResourceNotFoundException;
import com.spiny.spiny_demo.Repository.CarRepository;
import com.spiny.spiny_demo.Repository.UserRepository;
import com.spiny.spiny_demo.entity.Car;
import com.spiny.spiny_demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class BuyerController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;


    private User getAuthenticatedBuyer() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer not found: " + email));
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
    @GetMapping("/status")
    public List<Car> getByStatus(@RequestParam CarStatus status) {
        return carRepository.findByStatus(status);
    }
    @PutMapping("/book/{carId}")
    public ResponseEntity<?> bookCar(@PathVariable Long carId) {


        User buyer = getAuthenticatedBuyer();
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + carId));

        if (car.getStatus() != CarStatus.AVAILABLE) {
            return ResponseEntity.badRequest().body("Car is not available for booking.");
        }

        car.setStatus(CarStatus.PENDING); // or SOLD, depending on your business rule
        car.setBookedBy(buyer);
        carRepository.save(car);

        return ResponseEntity.ok("Car booked successfully. Status set to PENDING.");
    }

    @GetMapping("/brand")
    public List<Car> getByBrand(@RequestParam String brand) {
        return carRepository.findByBrand(brand);
    }

    @GetMapping("/model")
    public List<Car> getByModel(@RequestParam String model) {
        return carRepository.findByModel(model);
    }

    @GetMapping("/brand-model")
    public List<Car> getByBrandModel(@RequestParam String brand, @RequestParam String model) {
        return carRepository.findByBrandAndModel(brand, model);
    }

    @GetMapping("/price/max")
    public List<Car> getByMaxPrice(@RequestParam Double price) {
        return carRepository.findByPriceLessThanEqual(price);
    }
    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
    }


    @GetMapping("/price/min")
    public List<Car> getByMinPrice(@RequestParam Double price) {
        return carRepository.findByPriceGreaterThanEqual(price);
    }

    @GetMapping("/km/max")
    public List<Car> getByMaxKm(@RequestParam Integer kmDriven) {
        return carRepository.findByKmDrivenLessThanEqual(kmDriven);
    }

    @GetMapping("/km/min")
    public List<Car> getByMinKm(@RequestParam Integer kmDriven) {
        return carRepository.findByKmDrivenGreaterThanEqual(kmDriven);
    }

    @GetMapping("/fuel")
    public List<Car> getByFuelType(@RequestParam String fuelType) {
        return carRepository.findByFuelType(fuelType);
    }
}

