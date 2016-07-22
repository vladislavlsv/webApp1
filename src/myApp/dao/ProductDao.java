package myApp.dao;

import myApp.Exceptions.DaoSysException;
import myApp.Exceptions.NoSuchEntityException;
import myApp.entity.Product;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ProductDao {
    private final Map<Integer,Product> memory = new ConcurrentHashMap<>();

    public ProductDao() {
        this.memory.put(1, new Product(1,"Mousepad"));
        this.memory.put(2, new Product(2,"Mouse"));
        this.memory.put(3, new Product(3,"Keyboard"));
    }

    public Product selectById(int id) throws DaoSysException, NoSuchEntityException {
        if (!memory.containsKey(id)) {
            throw new NoSuchEntityException("Sry, Product is not exist");
        }
        return memory.get(id);
    }
    public List<Product> selectAll() throws DaoSysException{
        return new ArrayList<Product>(memory.values());
    }
}
