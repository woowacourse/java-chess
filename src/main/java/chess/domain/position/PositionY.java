package chess.domain.position;

public enum PositionY {
    RANK_1("rank 1", 1),
    RANK_2("rank 2", 2),
    RANK_3("rank 3", 3),
    RANK_4("rank 4", 4),
    RANK_5("rank 5", 5),
    RANK_6("rank 6", 6),
    RANK_7("rank 7", 7),
    RANK_8("rank 8", 8);

    private final String name;
    private final int coordination;

    PositionY(final String name, final int coordination) {
        this.name = name;
        this.coordination = coordination;
    }
}
