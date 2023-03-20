package chess.controller;

public class InvalidCommand extends Command {

    public static final InvalidCommand INSTANCE = new InvalidCommand(null);

    public InvalidCommand(Action action) {
        super(action);
    }

    @Override
    public boolean execute() {
        throw new IllegalArgumentException("존재하지 않는 입력입니다.");
    }
}
