package chess.domain.player;

public enum Turn {
    BLACK(0), WHITE(1);

    private final int index;

    Turn(int index) {
        this.index = index;
    }

    public Turn change() {
        if (this.equals(BLACK)) {
            return WHITE;
        }

        return BLACK;
    }

    public int index() {
        return index;
    }

    public int otherIndex() {
        return change().index;
    }
}
