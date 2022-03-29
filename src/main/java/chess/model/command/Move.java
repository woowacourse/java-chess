package chess.model.command;

public class Move extends Command {

    private static final String EMPTY_DELIMITER = " ";

    public Move(String input) {
        super(input);
    }

    @Override
    public String getSourcePosition() {
        return getCommandPosition().get(0);
    }

    @Override
    public String getTargetPosition() {
        return getCommandPosition().get(1);
    }

    @Override
    public Command turnState(String input) {
        if ("end".equals(input)) {
            return new End(input);
        }
        if (input.contains("move")) {
            return new Move(input);
        }
        throw new IllegalArgumentException("command has only move or end ");
    }

    public Command turnFinalState(String input) {
        if ("status".equals(input)) {
            return new Status(input);
        }
        return new End(input);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isMove() {
        return true;
    }
}
