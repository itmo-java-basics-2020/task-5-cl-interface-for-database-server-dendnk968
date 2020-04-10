package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.logic.Database;

import java.util.HashMap;
import java.util.Optional;

public class Environment implements ExecutionEnvironment {

    private HashMap<String, Database> map = new HashMap<>();

    @Override
    public Optional<Database> getDatabase(String name) {
        return Optional.of(map.getOrDefault(name, null));
    }

    @Override
    public void addDatabase(Database db) {
        map.put(db.getName(), db);
    }
}
