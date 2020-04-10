package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.DB;
import ru.andrey.kvstorage.logic.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateDatabase implements DatabaseCommand {
    private final String dbName;
    private ExecutionEnvironment env;

    CreateDatabase(String dbName, ExecutionEnvironment env) {
        this.dbName = dbName;
        this.env = env;
    }


    @Override
    public DatabaseCommandResult execute() {
        if (Files.exists(Paths.get("db/" + dbName)))
            return DatabaseResult.error("База данных с таким именем уже существует");
        try {
            Files.createDirectory(Paths.get("db/" + dbName));
            env.addDatabase(new DB(dbName));
            return DatabaseResult.succsess("");
        } catch (IOException e) {
            return DatabaseResult.error("Не удалось создать таблицу");
        }
    }


}
