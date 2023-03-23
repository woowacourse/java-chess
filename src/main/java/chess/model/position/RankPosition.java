package chess.model.position;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class RankPosition {

    private final Map<Rank, Position> rankPosition;

    private RankPosition(final Map<Rank, Position> rankPosition) {
        this.rankPosition = rankPosition;
    }

    static RankPosition create(final File file) {
        final Map<Rank, Position> collect = Arrays.stream(Rank.values())
                .collect(Collectors.toMap(rank -> rank, rank -> new Position(file, rank)));

        return new RankPosition(collect);
    }

    public Position findByRank(final Rank rank) {
        return rankPosition.get(rank);
    }
}
