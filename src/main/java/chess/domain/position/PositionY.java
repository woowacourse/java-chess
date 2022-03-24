package chess.domain.position;

import java.util.Arrays;

public enum PositionY {
    RANK_1("1", 7),
    RANK_2("2", 6),
    RANK_3("3", 5),
    RANK_4("4", 4),
    RANK_5("5", 3),
    RANK_6("6", 2),
    RANK_7("7", 1),
    RANK_8("8", 0);

    private final String name;
    private final int coordination;

    PositionY(final String name, final int coordination) {
        this.name = name;
        this.coordination = coordination;
    }

    public static PositionY of(String name) {
        return Arrays.stream(values())
                .filter(positionY -> positionY.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 좌표입니다."));
    }

    public static PositionY of(int coordination) {
        return Arrays.stream(values())
                .filter(positionY -> positionY.coordination == coordination)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 좌표입니다."));
    }

    public int displacementFrom(PositionY positionY) {
        return positionY.coordination - coordination;
    }

    public PositionY displace(int displacement) {
        return PositionY.of(coordination + displacement);
    }

    public String getName() {
        return name;
    }
}
