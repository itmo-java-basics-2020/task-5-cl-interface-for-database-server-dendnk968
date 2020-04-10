package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;

import java.util.Arrays;


public enum CommandCreator {
    CREATE_DATABASE((env, args) -> {
        if(args.length != 2) return () -> (DatabaseCommandResult) DatabaseResult.error("Не правилльная комманда");
        return new CreateDatabase(args[1], env);
    }),
    CREATE_TABLE((env, args) -> {

        if(args.length != 3) return () -> (DatabaseCommandResult) DatabaseResult.error("Не правилльная комманда");
        return new CreateTable(env, args[1], args[2]);
    }),
    READ_KEY((env, args) -> {

        if(args.length != 4) return () -> (DatabaseCommandResult) DatabaseResult.error("Не правилльная комманда");
        return new ReadValue(env, args[1], args[2], args[3]);
    }),
    UPDATE_KEY((env, args) -> {

        if(args.length != 5) return () -> (DatabaseCommandResult) DatabaseResult.error("Не правилльная комманда");
        return new UpdateKey(env, args[1], args[2], args[3], args[4]);
    });

    private static DatabaseCommand err = () -> (DatabaseCommandResult) DatabaseResult.error("Не правилльная комманда");
    private ICommandCreator create;

    CommandCreator(ICommandCreator create) {
        this.create = create;
    }


    public static DatabaseCommand getCommand(ExecutionEnvironment env, String[] args) {
        try {

            return CommandCreator.valueOf(args[0]).create.getCommand(env, args);
        } catch (IllegalArgumentException ex) {
            return err;
        }
    }
}

