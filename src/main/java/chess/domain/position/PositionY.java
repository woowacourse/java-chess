package chess.domain.position;

import java.util.Arrays;

public enum PositionY {
    RANK_1("rank 1", 7),
    RANK_2("rank 2", 6),
    RANK_3("rank 3", 5),
    RANK_4("rank 4", 4),
    RANK_5("rank 5", 3),
    RANK_6("rank 6", 2),
    RANK_7("rank 7", 1),
    RANK_8("rank 8", 0);

    private final String name;
    private final int coordination;

    PositionY(final String name, final int coordination) {
        this.name = name;
        this.coordination = coordination;
    }

    public static PositionY find(int coordination) {
        return Arrays.stream(values())
                .filter(positionY -> positionY.coordination == coordination)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 좌표입니다."));
    }

    public String getName() {
        return name;
    }

    public int getCoordination() {
        return coordination;
    }
}
