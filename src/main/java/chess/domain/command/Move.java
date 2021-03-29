package chess.domain.command;

public class Move implements Command {
    @Override
    public Command execute(final String command) {
        throw new UnsupportedOperationException("이미 움직이는 상태입니다.");
    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isMove() {
        return true;
    }

    @Override
    public boolean isStatus() {
        return false;
    }
}
