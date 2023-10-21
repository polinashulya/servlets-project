package repository;

import java.util.List;

public interface CrudRepository<T>  {
    T getEntityById(String id) ;

    boolean removeById(String id);

    boolean add(T t);

    boolean update(T t);

    List<T> getAll() ;

}
