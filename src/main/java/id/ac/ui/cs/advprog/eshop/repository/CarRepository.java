package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class CarRepository implements RepositoryBase<Car>{
    static int id = 0;
    private List<Car> carData = new ArrayList<>();
    public Car create(Car car){
        if(car.getCarId() == null){
            UUID uuid = UUID.randomUUID();
            car.setCarId(uuid.toString());
        }
        carData.add(car);
        return car;
    }
    public Iterator<Car> findAll(){
        return carData.iterator();
    }
    public Car findById(String id){
        for (Car car : carData) {
            if (car.getCarId().equals(id)) {
                return car;
            }
        }
        return null;
    }
    public Car update(String id, Car updatedCar) {
        for (int i = 0; 1 < carData.size(); i++){
            Car car = carData.get(i);
            if (car.getCarId().equals(id)){
                car.setCarName(updatedCar.getCarName());
                car.setCarColor(updatedCar.getCarColor());
                car.setCarQuantity(updatedCar.getCarQuantity());
                return car;
            }
        }
        return null;
    }
    public Car delete(String id) {
        Iterator<Car> iterator = carData.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (car.getCarId().equals(id)) {
                iterator.remove();
                return car;
            }
        }
        return null;
    }
}
