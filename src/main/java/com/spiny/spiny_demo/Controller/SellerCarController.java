package com.spiny.spiny_demo.Controller;

import com.spiny.spiny_demo.Exception.ResourceNotFoundException;
import com.spiny.spiny_demo.Repository.CarRepository;
import com.spiny.spiny_demo.Repository.UserRepository;
import com.spiny.spiny_demo.entity.Car;
import com.spiny.spiny_demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller/cars")
@PreAuthorize("hasRole('SELLER')")
public class SellerCarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadCar(@RequestBody Car car) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User seller = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found: " + username));

        car.setSeller(seller);
        carRepository.save(car);
        return ResponseEntity.ok("Car uploaded successfully.");
    }

    @GetMapping
    public List<Car> getSellerCars() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User seller = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found: " + username));
        return carRepository.findBySeller(seller);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User seller = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found: " + username));

        if (!existingCar.getSeller().getId().equals(seller.getId())) {
            return ResponseEntity.status(403).body("Unauthorized");
        }

        // Update fields
        existingCar.setBrand(updatedCar.getBrand());
        existingCar.setModel(updatedCar.getModel());
        existingCar.setPrice(updatedCar.getPrice());
        existingCar.setKmDriven(updatedCar.getKmDriven());
        existingCar.setFuelType(updatedCar.getFuelType());
        // Add any other fields you want to update here

        carRepository.save(existingCar);
        return ResponseEntity.ok("Car updated.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User seller = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found: " + username));

        if (!car.getSeller().getId().equals(seller.getId())) {
            return ResponseEntity.status(403).body("Unauthorized");
        }

        carRepository.delete(car);
        return ResponseEntity.ok("Car deleted.");
    }
}
