package chess.domain.command;

import chess.domain.position.Position;

public class Status implements Command {

    public static final String CANNOT_HAVE_POSITION = "Status 커맨드에선 위치 정보를 불러올 수 없습니다.";

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return true;
    }

    @Override
    public Position getFromPosition() {
        throw new IllegalStateException(CANNOT_HAVE_POSITION);
    }

    @Override
    public Position getToPosition() {
        throw new IllegalStateException(CANNOT_HAVE_POSITION);
    }
}
