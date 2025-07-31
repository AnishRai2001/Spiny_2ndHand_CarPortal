package com.spiny.spiny_demo.Controller;

import com.spiny.spiny_demo.Repository.CarRepository;
import com.spiny.spiny_demo.Repository.UserRepository;
import com.spiny.spiny_demo.Service.CarService;
import com.spiny.spiny_demo.ServiceImpl.CarServiceImpl;
import com.spiny.spiny_demo.entity.Car;
import com.spiny.spiny_demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarServiceImpl carService;


    @PostMapping("/upload")
    public ResponseEntity<?> uploadCarDetails(@RequestBody Car car) {


        carRepository.save(car);

        return ResponseEntity.ok("Car saved successfully.");
    }
@GetMapping
public List<Car> findAllCar() {
      return  carService.finAllCar();

}

    @GetMapping("/brand")
    public List<Car> getCarsByBrand(@RequestParam String brand) {
        return carRepository.findByBrand(brand);
    }

    @GetMapping("/brand-model")
    public List<Car> getCarsByBrandAndModel(@RequestParam String brand, @RequestParam String model) {
        return carRepository.findByBrandAndModel(brand, model);
    }

    @GetMapping("/model")
    public List<Car> getCarsByModel(@RequestParam String model) {
        return carRepository.findByModel(model);
    }

    @GetMapping("/price/max")
    public List<Car> getCarsByMaxPrice(@RequestParam Double price) {
        return carRepository.findByPriceLessThanEqual(price);
    }

    @GetMapping("/price/min")
    public List<Car> getCarsByMinPrice(@RequestParam Double price) {
        return carRepository.findByPriceGreaterThanEqual(price);
    }

    @GetMapping("/km/max")
    public List<Car> getCarsByMaxKm(@RequestParam Integer kmDriven) {
        return carRepository.findByKmDrivenLessThanEqual(kmDriven);
    }

    @GetMapping("/km/min")
    public List<Car> getCarsByMinKm(@RequestParam Integer kmDriven) {
        return carRepository.findByKmDrivenGreaterThanEqual(kmDriven);
    }

    @GetMapping("/fuel")
    public List<Car> getCarsByFuelType(@RequestParam String fuelType) {
        return carRepository.findByFuelType(fuelType);
    }
}

