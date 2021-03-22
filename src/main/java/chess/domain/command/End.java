package chess.domain.command;

public class End implements Command {
    @Override
    public Command execute(final String command) {
        throw new UnsupportedOperationException("이미 끝난 상태입니다.");
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
}
