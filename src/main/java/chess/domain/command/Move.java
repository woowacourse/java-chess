package chess.domain.command;

public class Move extends Start {
    Move() {
    }

    @Override
    public CommandState execute(String input) {
        return super.execute(input);
    }

    @Override
    public boolean isMove() {
        return true;
    }
}
