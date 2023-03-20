package chess.controller;

public class EndCommand extends Command {

    public EndCommand(Action action) {
        super(action);
    }

    @Override
    public boolean execute() {
        return false;
    }
}
