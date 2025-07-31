package com.spiny.spiny_demo.Service;

import com.spiny.spiny_demo.Dto.CarFilter;
import com.spiny.spiny_demo.entity.Car;

import java.util.List;

public interface CarService {
    Car uploadCarDetails(CarFilter carFilter);

    List<Car> finAllCar();
}
