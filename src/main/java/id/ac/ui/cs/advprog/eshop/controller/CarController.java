package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;
//slight changes
@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarServiceImpl carservice;

    @GetMapping("/createCar")
    public String createCarPage(Model model){
        Car car = new Car();
        model.addAttribute("car", car);
        return "createCar";
    }
    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car, Model model){
        carservice.create(car);
        return "redirect:listCar";
    }
    @GetMapping("/listCar")
    public
    String carListPage(Model model){
        List<Car> allCars = carservice.findAll();
        model.addAttribute("cars", allCars);
        return "carList";
    }
    @GetMapping("/editCar/{id}")
    public String editCarPage(@PathVariable String id, Model model){
        Car car = carservice.findById(id);
        model.addAttribute("car", car);
        return "editCar";
    }
    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car, Model model){
        System.out.println(car.getCarId());
        carservice.update(car.getCarId(), car);

        return "redirect:listCar";
    }
    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") String carId) {
        carservice.deleteCarById(carId);
        return "redirect:listCar";
    }

}