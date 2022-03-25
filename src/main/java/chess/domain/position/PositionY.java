package chess.domain.position;

import java.util.Arrays;
import java.util.Comparator;

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

    public int displacementTo(PositionY positionY) {
        return positionY.coordination - coordination;
    }

    public PositionY displacedOf(int displacement) {
        return PositionY.of(coordination + displacement);
    }

    public boolean isFirstOrLastRank() {
        Comparator<PositionY> comparatorByCoordination = Comparator.comparingInt(positionY -> positionY.coordination);

        PositionY firstRank = Arrays.stream(values())
                .min(comparatorByCoordination)
                .orElseThrow(() -> new IllegalArgumentException("세로줄은 1개 이상 존재해야 합니다."));
        PositionY lastRank = Arrays.stream(values())
                .max(comparatorByCoordination)
                .orElseThrow(() -> new IllegalArgumentException("세로줄은 1개 이상 존재해야 합니다."));

        return (this.equals(firstRank) || this.equals(lastRank));
    }

    public String getName() {
        return name;
    }


}
