package chess.controller;

public enum GameCommand {
    START(0),
    MOVE(2),
    END(0),
    STATUS(0),
    EMPTY(0),
    CLEAR(0),
    ;

    private final int parameterLength;

    GameCommand(int parameterLength) {
        this.parameterLength = parameterLength;
    }

    public int getParameterLength() {
        return parameterLength;
    }
}
