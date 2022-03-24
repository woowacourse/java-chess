package chess.command;

public class End extends Command{

    public End(String input) {
        super(input);
    }

    @Override
    public Command turnState(String input) {
        return null;
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
