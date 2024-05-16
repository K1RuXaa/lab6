package ru.itmo.server.colletions;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itmo.general.managers.CollectionManager;
import ru.itmo.general.models.SpaceMarine;
import java.util.Collections;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

public class SpaceMarineCollectionManager implements CollectionManager<SpaceMarine> {
    private final Logger logger = LoggerFactory.getLogger("SpaceMarineCollectionManager");
    @Getter
    private final LinkedList<SpaceMarine> collection = new LinkedList<>();
    private int currentId = 1;
    @Getter
    private LocalDateTime lastSaveTime;

    @Override
    public void validateAll() {

    }
    @Override
    public int collectionSize() {
        return collection.size();
    }

    @Override
    public SpaceMarine byId(long id) {

            if (collection.isEmpty()) return null;
            return collection.stream()
                    .filter(ticket -> ticket.getId() == id)
                    .findFirst()
                    .orElse(null);

    }

    @Override
    public boolean contains(SpaceMarine item) {
        return collection.contains(item);
    }

    @Override
    public boolean add(SpaceMarine sp) {
        if (contains(sp)) {
            return false;
        }
        collection.add(sp);
        update();
        return true;
    }

    @Override
    public boolean update(SpaceMarine item) {
        return false;
    }

    @Override
    public boolean remove(long id) {
        SpaceMarine marine = byId(id);
        if (marine == null){
            return false;
        }
        if (id != marine.getId() ) return false;
        collection.remove(marine);
        update();
        return true;
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";

        StringBuilder info = new StringBuilder("SpaceMarineCollectionManager{" +
                ", collection=" + '\n');
        for (var Ticket : collection) {
            info.append(Ticket).append("\n\n");
        }
        info.append('}');
        return info.toString().trim();
    }

    public void addAll(Collection<SpaceMarine> marines) {
        for (SpaceMarine sp : marines) {
            if (!contains(sp)) {
                collection.add(sp);
            }
        }
    }

    @Override
    public boolean remove(SpaceMarine sp) {
        return collection.remove(sp);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean loadCollection() {
        return false;
    }

    @Override
    public void clearCollection() {

    }



    @Override
    public SpaceMarine getFirst() {
        return null;
    }

    @Override
    public String collectionType() {
        return collection.getClass().getName();
    }

    @Override
    public SpaceMarine getLast() {
        return null;
    }



}
