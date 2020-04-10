package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

public class UpdateKey implements DatabaseCommand {

    private ExecutionEnvironment env;
    private String dbName, table, key, value;


    public UpdateKey(ExecutionEnvironment env, String dbName, String table, String key, String value) {
        this.env = env;
        this.dbName = dbName;
        this.table = table;
        this.key = key;
        this.value = value;
    }

    @Override
    public DatabaseCommandResult execute() {
        try {
            Database db = env.getDatabase(dbName).get();
            if(db == null) return DatabaseResult.error("База данных не существует");
            db.write(table, key, value);
            return DatabaseResult.succsess(value);
        } catch (DatabaseException e) {
            return DatabaseResult.error(e.getMessage());
        }
    }
}
