package ru.itmo.general.managers;

import ru.itmo.general.models.SpaceMarine;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface for managing a collection of objects.
 *
 * @param <T> the type of objects in the collection
 */
public interface CollectionManager<T> {
    /**
     * Validates all objects in the collection.
     */
    void validateAll();

    /**
     * Gets the collection of objects.
     *
     * @return the collection of objects
     */
    List<T> getCollection();

    /**
     * Gets an object by its identifier.
     *
     * @param id the identifier of the object
     * @return the object with the specified identifier, or null if no such object is found
     */

    SpaceMarine byId(long id);

    /**
     * Checks if the collection contains the specified object.
     *
     * @param item the object to check
     * @return true if the collection contains the specified object, otherwise false
     */
    boolean contains(T item);

    /**
     * Adds an object to the collection.
     *
     * @param item   the object to add
     * @param userID the ID of the user adding the object
     * @return true if the object is successfully added, otherwise false
     */
    boolean add(T item);

    /**
     * Updates the information about an object in the collection.
     *
     * @param item the object to update
     * @return true if the object is successfully updated, otherwise false
     */
    boolean update(T item);

    /**
     * Removes an object from the collection by its identifier.
     *
     * @param id the identifier of the object to remove
     * @return true if the object is successfully removed, otherwise false
     */
    boolean remove(long id);

    /**
     * Removes the specified object from the collection.
     *
     * @param item the object to remove
     * @return true if the object is successfully removed, otherwise false
     */
    boolean remove(T item);

    /**
     * Updates the state of the collection.
     */
    void update();

    /**
     * Loads the collection from a file.
     *
     * @return true if the collection is successfully loaded, otherwise false
     */
    boolean loadCollection();

    /**
     * Clears the collection.
     */
    void clearCollection();

    /**
     * Gets the size of the collection.
     *
     * @return the size of the collection
     */
    int collectionSize();

    /**
     * Gets the first object in the collection.
     *
     * @return the first object in the collection, or null if the collection is empty
     */
    T getFirst();

    /**
     * Gets the date and time of the last save operation.
     *
     * @return the date and time of the last save operation
     */
    LocalDateTime getLastSaveTime();

    /**
     * Gets the type of the collection.
     *
     * @return the type of the collection
     */
    String collectionType();

    /**
     * Gets the last object in the collection.
     *
     * @return the last object in the collection, or null if the collection is empty
     */
    T getLast();
}