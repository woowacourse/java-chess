package chess.domain.command;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;

public enum SingleCommand implements Command {
    START,
    END,
    STATUS;

    private static final String CANNOT_HAVE_POSITION = "현재 토큰에서는 위치 정보를 불러올 수 없습니다.";

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
        throw new IllegalArgumentException(CANNOT_HAVE_POSITION);
    }

    @Override
    public Position getToPosition() {
        throw new IllegalArgumentException(CANNOT_HAVE_POSITION);
    }
}
