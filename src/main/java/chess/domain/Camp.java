package chess.domain;

public enum Camp {
    WHITE(1, true),
    BLACK(-1, false),
    NONE(0, false);

    private final int verticalDirection;
    private boolean turn;

    Camp(int verticalDirection, boolean turn) {
        this.verticalDirection = verticalDirection;
        this.turn = turn;
    }

    public static void initializeTurn() {
        WHITE.turn = true;
        BLACK.turn = false;
    }

    public static void switchTurn() {
        WHITE.turn = !WHITE.turn;
        BLACK.turn = !BLACK.turn;
    }

    public boolean isNotTurn() {
        return !this.turn;
    }

    public int giveVerticalDirectionTo(int distance) {
        return distance * this.verticalDirection;
    }

    @Override
    public String toString() {
        if (this == NONE) {
            return "";
        }
        return name().toLowerCase();
    }
}
