package com.spiny.spiny_demo.Repository;

import com.spiny.spiny_demo.CarStatus;
import com.spiny.spiny_demo.entity.Car;
import com.spiny.spiny_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {


        List<Car> findByBrand(String brand);

        List<Car> findByBrandAndModel(String brand, String model);

        List<Car> findByModel(String model);

        List<Car> findByPriceLessThanEqual(Double price);
        List<Car> findByPriceGreaterThanEqual(Double price);
        List<Car> findByKmDrivenLessThanEqual(Integer kmDriven);
        List<Car> findByKmDrivenGreaterThanEqual(Integer kmDriven);

        List<Car> findByFuelType(String fuelType);

        List<Car> findBySeller(User seller);


        List<Car> findByStatus(CarStatus status);

}

