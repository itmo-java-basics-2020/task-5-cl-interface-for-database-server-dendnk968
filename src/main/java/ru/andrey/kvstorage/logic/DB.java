package ru.andrey.kvstorage.logic;

import ru.andrey.kvstorage.console.DatabaseResult;
import ru.andrey.kvstorage.exception.DatabaseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class DB implements Database {
    private String name;

    public DB(String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void createTableIfNotExists(String tableName) throws DatabaseException {
        if (Files.exists(Paths.get("db/" + tableName)))
            throw new DatabaseException("База данных уже существует");

        try {
            Files.createDirectory(Paths.get("db/" + tableName));
        } catch (IOException e) {
            throw new DatabaseException("Не удалось создать таблицу");
        }
    }

    @Override
    public void createTableIfNotExists(String tableName, int segmentSizeInBytes) throws DatabaseException {

    }

    @Override
    public void write(String tableName, String objectKey, String objectValue) throws DatabaseException {
        if(Files.notExists(Paths.get("db/" + tableName))) throw new DatabaseException("Таблицы с таким именем не существует");
        File file = new File("db/" + tableName + "/" + objectKey + ".key");
        try {
            FileWriter out = new FileWriter(file);
            out.write(objectValue);
            out.close();
        } catch (IOException e) {
            throw new DatabaseException("Не удалось сохранить значение");
        }
    }

    @Override
    public String read(String tableName, String objectKey) throws DatabaseException {
        if(Files.notExists(Paths.get("db/" + tableName))) throw new DatabaseException("Таблицы с таким именем не существует");
        File file = new File("db/" + tableName + "/" + objectKey + ".key");
        if(file.exists()) throw new DatabaseException("Обьекта по такому ключу не найдено");
        try {
            Scanner scanner = new Scanner(new FileInputStream(file));
            return scanner.nextLine();
        } catch (FileNotFoundException e) {
            throw new DatabaseException("Не получилось получить значение");
        }

    }
}
