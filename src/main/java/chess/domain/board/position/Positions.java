package chess.domain.board.position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Positions {

    private final static List<Position> positions;

    static {
        positions = Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values())
                        .map(rank -> new Position(file, rank)))
                .collect(Collectors.toUnmodifiableList());
    }

    private Positions() {
    }

    public static Position findPositionBy(File file, Rank rank) {
        return positions
                .stream()
                .filter(position -> position.isSameFileAndRank(file, rank))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재 하지 않는 Position 입니다."));
    }

    public static Position findPositionBy(String rankFile) {
        File file = File.of(rankFile.substring(0, 1));
        Rank rank = Rank.of(rankFile.substring(1, 2));

        return findPositionBy(file, rank);
    }

    public static Position findPositionBy(int file, int rank) {
        return findPositionBy(File.of(file), Rank.of(rank));
    }
}
