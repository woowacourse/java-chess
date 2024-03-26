package chess.domain;

public enum Rank {

    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    ;

    public Rank moveByOffset(int offset) {
        try {
            return values()[this.ordinal() + offset];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("체스판 세로의 범위를 넘어갔습니다.");
        }
    }

    public int calculateDifference(Rank rank) {
        return this.ordinal() - rank.ordinal();
    }
}
