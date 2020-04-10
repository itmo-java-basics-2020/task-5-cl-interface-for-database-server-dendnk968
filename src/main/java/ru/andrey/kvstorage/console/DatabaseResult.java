package ru.andrey.kvstorage.console;

import java.util.Optional;

public class DatabaseResult implements DatabaseCommandResult {

    private String error;
    private DatabaseCommandStatus status;
    private Optional<String> result;

    private DatabaseResult() {
    }

    public static DatabaseCommandResult succsess(String res) {
        DatabaseResult result = new DatabaseResult();
        result.result = Optional.of(res);
        result.status = DatabaseCommandStatus.SUCCESS;
        return result;
    }

    public static DatabaseCommandResult error(String error) {
        DatabaseResult result = new DatabaseResult();
        result.error = error;
        result.status = DatabaseCommandStatus.FAILED;
        return result;
    }

    @Override
    public Optional<String> getResult() {
        return result;
    }

    @Override
    public DatabaseCommandStatus getStatus() {
        return status;
    }

    @Override
    public boolean isSuccess() {
        return status == DatabaseCommandStatus.SUCCESS;
    }

    @Override
    public String getErrorMessage() {
        return error;
    }
}
