package chess.model.command;

public class End extends Command {

    public End(String input) {
        super(input);
    }

    @Override
    public Command turnState(String input) {
        return this;
    }

    @Override
    public Command turnFinalState(String input) {
        if (Command.STATUS.equals(input)) {
            return new Status(input);
        }
        return new End(input);
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
