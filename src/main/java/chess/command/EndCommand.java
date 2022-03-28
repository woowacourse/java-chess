package chess.command;

import chess.position.Position;

public class EndCommand implements Command {

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return false;
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
