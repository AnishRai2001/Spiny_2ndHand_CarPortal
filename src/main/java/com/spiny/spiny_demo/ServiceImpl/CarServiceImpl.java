package com.spiny.spiny_demo.ServiceImpl;

import com.spiny.spiny_demo.Dto.CarFilter;
import com.spiny.spiny_demo.Repository.CarRepository;
import com.spiny.spiny_demo.Service.CarService;
import com.spiny.spiny_demo.entity.Car;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car uploadCarDetails(CarFilter carFilter) {
        Car car = new Car();
        BeanUtils.copyProperties(carFilter, car);
        return carRepository.save(car);
    }

    @Override
    public List<Car> finAllCar() {
        List<Car>cars=carRepository.findAll();
        return cars;
    }
}
