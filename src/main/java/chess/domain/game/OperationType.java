package chess.domain.game;

import chess.domain.exception.WrongOperationException;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BooleanSupplier;

public enum OperationType {
    START("start", () -> {
        throw new WrongOperationException();
    }),
    MOVE("move", () -> true),
    END("end", () -> false),
    STATUS("status", () -> true);

    private final String name;
    private final BooleanSupplier operationHandler;

    OperationType(String name, BooleanSupplier operationHandler) {
        this.name = name;
        this.operationHandler = operationHandler;
    }

    public static OperationType of(String name) {
        validate(name);
        return Arrays.stream(OperationType.values())
                .filter(operationType -> operationType.name.equals(name))
                .findFirst()
                .orElseThrow(WrongOperationException::new);
    }

    private static void validate(String name) {
        Objects.requireNonNull(name, "NULL 값을 입력하셨습니다.");
        if (name.isEmpty()) {
            throw new WrongOperationException();
        }
    }

    public void checkFirstOperations() {
        if (!isStart() && !isEnd()) {
            throw new WrongOperationException();
        }
    }

    public boolean canExecuteMore() {
        return operationHandler.getAsBoolean();
    }

    public boolean isStart() {
        return START.equals(this);
    }

    public boolean isEnd() {
        return END.equals(this);
    }

    public boolean isMove() {
        return MOVE.equals(this);
    }

    public boolean isStatus() {
        return STATUS.equals(this);
    }
}
