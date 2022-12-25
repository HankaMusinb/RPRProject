package ba.unsa.etf.rpr.dao;
import java.sql.SQLException;
import java.util.List;

/**
 * Root interface for all DAO classes
 *
 * @author Hanka Musinbegovic
 */
public interface Dao<T>{
    /**
     * Get item from database based on ID
     * @param id primary key of entity
     * @return item from db
     */
    T getById(int id) throws SQLException;

    /**
     * Add item to workshop
     * @param item represents new item to be saved to database
     * @return returns item with its id
     */
    T add(T item) throws SQLException;

    /**
     * Updates all items in workshop
     * @param item
     * @return updated version of the item
     */
    T update(T item) throws SQLException;

    /**
     * Delete item with given id from workshop
     * @param id - primary key of item to be deleted
     */
    void delete(int id);

    /**
     * Lists all items in workshop placed in db.
     * @return List of all items.
     */
    List<T> getAll() throws SQLException;
}
