package chess.domain.command;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;

public enum SingleCommand implements Command {
    START,
    END,
    STATUS;

    @Override
    public boolean isStart() {
        return this == START;
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return this == STATUS;
    }

    @Override
    public Position getFromPosition() {
        throw new IllegalArgumentException();
    }

    @Override
    public Position getToPosition() {
        throw new IllegalArgumentException();
    }
}
