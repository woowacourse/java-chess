package chess.domain.position.cache;

import chess.domain.position.Position;
import chess.domain.position.type.File;
import chess.domain.position.type.Rank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PositionsCache {
    private static final List<Position> positions = new ArrayList<>();

    static {
        cachePositions();
    }

    private static void cachePositions() {
        List<Rank> ranks = Arrays.asList(Rank.values());
        List<Rank> reversedRanks = ranks.stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        for (Rank rank : reversedRanks) {
            cachingPositionsOfRank(rank);
        }
    }

    private static void cachingPositionsOfRank(Rank rank) {
        for (File file : File.values()) {
            positions.add(new Position(file, rank));
        }
    }

    public static Position find(File file, Rank rank) {
        return positions.stream()
            .filter(position -> position.file() == file && position.rank() == rank)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 위치 입니다."));
    }

    public static Position get(int index) {
        return positions.get(index);
    }
}
