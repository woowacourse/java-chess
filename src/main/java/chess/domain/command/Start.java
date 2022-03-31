package chess.domain.command;

public class Start extends CommandState {

    Start() {
    }

    @Override
    public boolean isStart() {
        return true;
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
        return false;
    }

    @Override
    public CommandState execute(String input) {
        return new Start();
    }
}
