package chess.command;

public class Status implements Command {
    protected Status() {
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
        return false;
    }

    @Override
    public boolean isStatus() {
        return true;
    }
}
