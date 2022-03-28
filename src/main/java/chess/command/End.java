package chess.command;

public class End implements Command {
    protected End() {
    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean isStatus() {
        return false;
    }
}
