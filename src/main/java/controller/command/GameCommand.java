package controller.command;

public abstract class GameCommand implements Command {

    protected enum GameCommandType {
        MOVE,
        STATUS,
        END;
    }

    public boolean isGameCommands(String input) {
        try {
            GameCommandType.valueOf(input.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
