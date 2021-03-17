package chess.domain.board;

public enum YPosition {
    Eight(8),
    Seven(7),
    Six(6),
    Five(5),
    Four(4),
    Three(3),
    Two(2),
    One(1);

    private final int yPosition;

    YPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getYPosition() {
        return this.yPosition;
    }
}
