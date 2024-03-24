package domain.game;

public enum GameCommand {
    START, END, MOVE;

    public boolean isStartCommand() {
        return this == START;
    }

    public boolean isMoveCommand() {
        return this == MOVE;
    }
}
