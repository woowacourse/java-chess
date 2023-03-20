package chess.controller;

public class Command {
    private final Action action;

    public Command(Action action) {
        this.action = action;
    }

    public boolean execute() {
        return action.execute();
    }
}
