package chess.domain.game;

import chess.domain.position.PositionFactory;
import chess.domain.util.WrongOperationException;

import java.util.Arrays;
import java.util.Objects;

public enum OperationType {
    START("start", (chessGame, operations) -> {
        throw new WrongOperationException();
    }),
    MOVE("move", (chessGame, operations) -> {
        chessGame.move(PositionFactory.of(operations.getFirstArgument()), PositionFactory.of(operations.getSecondArgument()));
        return true;
    }),
    END("end", (chessGame, operations) -> false),
    STATUS("status", (chessGame, operations) -> true);

    private final String name;
    private final Operate operate;

    OperationType(String name, Operate operate) {
        this.name = name;
        this.operate = operate;
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

    public boolean isStart() {
        return START.equals(this);
    }

    public boolean isEnd() {
        return END.equals(this);
    }

    public boolean runOperate(ChessGame chessGame, Operations operations) {
        return operate.apply(chessGame, operations);
    }
}
