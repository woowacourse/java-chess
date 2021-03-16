package chess.board;

public enum YPosition {
    One(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8);

    private final int yPosition;

    YPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getYPosition() {
        return this.yPosition;
    }
}
