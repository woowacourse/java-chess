package chess.domain;

public enum Rank {
    RANK1(1),
    RANK2(2),
    RANK3(3),
    RANK4(4),
    RANK5(5),
    RANK6(6),
    RANK7(7),
    RANK8(8);

    private final int number;

    Rank(final int number) {
        this.number = number;
    }
}
