package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.ExecutionEnvironment;

public interface ICommandCreator {
    DatabaseCommand getCommand(ExecutionEnvironment env, String args[]);
}
