package chess.domain.position;

import java.util.Arrays;

public enum Row {

    RANK_8("8", 0),
    RANK_7("7", 1),
    RANK_6("6", 2),
    RANK_5("5", 3),
    RANK_4("4", 4),
    RANK_3("3", 5),
    RANK_2("2", 6),
    RANK_1("1", 7);

    private final String name;
    private final int rank;

    Row(final String name, final int rank) {
        this.name = name;
        this.rank = rank;
    }

    public static Row of(String name) {
        return Arrays.stream(values())
                .filter(rank -> rank.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("rank" + name + " 은 유효하지 않은 좌표입니다."));
    }

    public static Row of(int rank) {
        return Arrays.stream(values())
                .filter(row -> row.rank == rank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("rank" + rank + " 은 유효하지 않은 좌표입니다."));
    }

    public int displacementTo(Row row) {
        return row.rank - rank;
    }

    public Row displacedOf(int displacement) {
        return Row.of(rank + displacement);
    }

    public String getName() {
        return name;
    }
}
