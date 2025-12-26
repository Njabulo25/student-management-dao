
package za.ac.tut.bl;

import java.util.List;

/**
 * Generic DAO interface defining standard CRUD operations for any entity type.
 *
 * @param <T> the entity type
 */

public interface DAO<T>
{
    
    T get(Integer code);
    
    List<T> getAll();
    
    boolean add(T t);
    
    boolean update(T t);
    
    boolean delete(T t);
}

