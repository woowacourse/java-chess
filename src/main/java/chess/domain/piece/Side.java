package chess.domain.piece;

public enum Side {

    BLACK,
    WHITE,
    NEUTRALITY;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isNeutrality() {
        return this == NEUTRALITY;
    }

    public boolean isAlly(final Side other) {
        if (this.isNeutrality()) {
            throw new IllegalArgumentException("중립은 아군이 존재하지 않습니다.");
        }
        return this == other;
    }

    public boolean isEnemy(final Side other) {
        if (this.isNeutrality()) {
            throw new IllegalArgumentException("중립은 적군이 존재하지 않습니다.");
        }
        return this != other && !other.isNeutrality();
    }
}
