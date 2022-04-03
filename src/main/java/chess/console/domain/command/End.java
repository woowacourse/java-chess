package chess.console.domain.command;

public final class End extends CommandState {
    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isStatus() {
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
    public CommandState execute(String command) {
        throw new UnsupportedOperationException();
    }
}
