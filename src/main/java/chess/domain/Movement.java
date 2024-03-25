package chess.domain;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Movement {
    public static final String INVALID_PIECE_MOVEMENT = "해당 기물은 위치로 이동할 수 없습니다.";

    private final Square source;
    private final Square target;

    public Movement(final Square source, final Square target) {
        validateSameSquare(source, target);
        this.source = source;
        this.target = target;
    }


    private void validateSameSquare(final Square source, final Square target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException(INVALID_PIECE_MOVEMENT);
        }
    }

    public Set<Square> findRoute() {
        int maxDistance = calculateMaxDistance();
        Set<Square> route = new HashSet<>();
        IntStream.range(1, maxDistance)
                .forEach(distance -> route.add(findSquareBy(distance)));
        return route;
    }

    public int calculateMaxDistance() {
        return Math.max(Math.abs(getFileDifference()), Math.abs(getRankDifference()));
    }

    private Square findSquareBy(final int distance) {
        Rank nextRank = Rank.of(source.getRankIndex(), target.getRankIndex() * distance);
        File nextFile = File.of(source.getRankIndex(), target.getRankIndex() * distance);
        return Square.of(nextRank, nextFile);
    }

    public int getFileDifference() {
        return target.getFileIndex() - source.getFileIndex();
    }

    public int getRankDifference() {
        return target.getRankIndex() - source.getRankIndex();
    }

    public int getSourceRankIndex() {
        return source.getRankIndex();
    }

    public Direction direction() {
        int maxDistance = calculateMaxDistance();
        return Direction.of(getFileDifference() / maxDistance, getRankDifference() / maxDistance);
    }
}
