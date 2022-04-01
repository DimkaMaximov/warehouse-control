package liga.warehouse.warehousecontrol.api;

import java.util.List;

public interface AbstractService<T> {

    List<T> findAll();

    T findById(Long id);

    void insert(T t);

    void deleteById(Long userId);
}