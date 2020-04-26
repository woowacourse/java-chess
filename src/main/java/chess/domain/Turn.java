package chess.domain;

public enum Turn {
    BLACK(true),
    WHITE(false);

    private final boolean blackTurn;

    Turn(boolean blackTurn) {
        this.blackTurn = blackTurn;
    }

    public static Turn of(boolean blackTurn) {
        if (blackTurn) return BLACK;
        return WHITE;
    }

    public boolean getTurn() {
        return this.blackTurn;
    }

    public Turn getOppositeTurn() {
        return Turn.of(!this.blackTurn);
    }
}
