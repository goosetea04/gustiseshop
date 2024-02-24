package id.ac.ui.cs.advprog.eshop.repository;
import java.util.ArrayList;
import java.util.List;
import id.ac.ui.cs.advprog.eshop.model.Car;
public interface RepositoryBase<Type> {
    static int id = 0;

    Type create(Type inventory);
    Type delete(String id);
    List<Type> findAll();
    Type update(String id, Type updated);
    Type findById(String id);
}
