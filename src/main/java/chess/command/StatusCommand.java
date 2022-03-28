package chess.command;

import chess.position.Position;

public class StatusCommand implements Command {

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isStart() {
        return true;
    }

    @Override
    public boolean isEnd() {
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
    public Position from() {
        throw new UnsupportedOperationException("[ERROR] from을 지원하지 않습니다.");
    }

    @Override
    public Position to() {
        throw new UnsupportedOperationException("[ERROR] to를 지원하지 않습니다.");
    }
}
