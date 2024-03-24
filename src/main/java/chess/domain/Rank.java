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
            throw new IllegalArgumentException("[ERROR] 체스판 세로 범위를 넘어가도록 움직일 수는 없습니다.");
        }
    }

    public int calculateDifference(Rank rank) {
        return this.ordinal() - rank.ordinal();
    }
}
