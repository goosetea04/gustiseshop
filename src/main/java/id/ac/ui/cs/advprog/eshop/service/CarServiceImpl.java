package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class CarServiceImpl implements CarService{
    @Autowired
    private CarRepository carRepository;

    @Override
    public Car create(Car car){
        carRepository.create(car);
        return car;
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> iterator = carRepository.findAll();
        List<Car> carList = new ArrayList<>();
        while (iterator.hasNext()) {
            carList.add(iterator.next());
        }
        return carList;
    }

    @Override
    public Car findById(String carId){
        Car car = carRepository.findById(carId);
        return car;
    }

    @Override
    public Car update(String carId, Car car){
        carRepository.update(carId, car);
        return car;
    }

    @Override
    public void deleteCarById(String carId){
        carRepository.delete(carId);
    }
}