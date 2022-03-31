package chess.domain.command;

public class Status extends Start {

    Status() {
    }

    @Override
    public boolean isStatus() {
        return true;
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public CommandState execute(String input) {
        return new Start();
    }
}
