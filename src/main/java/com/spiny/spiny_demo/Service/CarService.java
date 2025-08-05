package com.spiny.spiny_demo.Service;

import com.spiny.spiny_demo.Dto.CarFilter;
import com.spiny.spiny_demo.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Car uploadCarDetails(CarFilter carFilter);

    List<Car> finAllCar();
    Optional<Car> findCarById(Long id);  // NEW method to find car by id safely
}

