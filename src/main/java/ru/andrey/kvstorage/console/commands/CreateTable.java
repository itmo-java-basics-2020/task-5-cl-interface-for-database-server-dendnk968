package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

public class CreateTable implements DatabaseCommand {
    private ExecutionEnvironment env;
    private String dbName, tableName;

    CreateTable(ExecutionEnvironment env, String dbName, String tableName) {
        this.env = env;
        this.dbName = dbName;
        this.tableName = tableName;
    }

    @Override
    public DatabaseCommandResult execute() {
        try {
            Database db = env.getDatabase(dbName).get();
            if(db == null) return DatabaseResult.error("Таблицы с таким именнем не существует");
            db.createTableIfNotExists(tableName);
        } catch (DatabaseException e) {
            return DatabaseResult.error(e.getMessage());
        }
        return DatabaseResult.succsess("");
    }
}
