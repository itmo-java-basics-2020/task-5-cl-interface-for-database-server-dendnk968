package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadValue implements DatabaseCommand {
    private ExecutionEnvironment env;
    private String dbName, table, key;

    public ReadValue(ExecutionEnvironment env, String dbName, String table, String key) {
        this.env = env;
        this.dbName = dbName;
        this.table = table;
        this.key = key;
    }

    @Override
    public DatabaseCommandResult execute() {
        try {
            Optional<Database> opt = env.getDatabase(dbName);
            if (opt.isEmpty()) return DatabaseResult.error("базы данных с таким именнем не существует");
            Database db = opt.get();
            return DatabaseResult.succsess(db.read(table, key));
        } catch (DatabaseException e) {
            return DatabaseResult.error(e.getMessage());
        }
    }
}
