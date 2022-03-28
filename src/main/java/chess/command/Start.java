package chess.command;

public class Start implements Command {
    protected Start() {
    }

    @Override
    public boolean isStart() {
        return true;
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
        return false;
    }
}
