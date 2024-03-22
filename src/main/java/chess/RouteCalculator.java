package chess;

import chess.domain.piece.File;
import chess.domain.piece.Position;
import chess.domain.piece.Rank;
import java.util.HashSet;
import java.util.Set;
public class RouteCalculator {

    public static Set<Position> getVerticalMiddlePositions(final Position current, final Position target) {
        Position position = getLowerPosition(current, target);

        final Set<Position> positions = new HashSet<>();
        for (int i = 0; i < current.getRankDistance(target) - 1; i++) {
            position = position.up();
            positions.add(position);
        }

        return positions;
    }

    private static Position getLowerPosition(final Position current, final Position target) {
        if (current.isDownWith(target)) {
            return current;
        }

        return target;
    }

    public static Set<Position> getHorizontalMiddlePositions(final Position current, final Position target) {
        Position position = getLefterPosition(current, target);

        final Set<Position> positions = new HashSet<>();
        for (int i = 0; i < current.getFileDistance(target) - 1; i++) {
            position = position.right();
            positions.add(position);
        }
        return positions;
    }

    private static Position getLefterPosition(final Position current, final Position target) {
        if (current.isLeftWith(target)) {
            return current;
        }

        return target;
    }

    public static Set<Position> getRightDiagonalMiddlePositions(final Position current, final Position target) {
        Position start = current;
        Position end = target;

        if (start.getRank().getIndex() > target.getRank().getIndex()) {
            start = target;
            end = current;
        }

        final Set<Position> positions = new HashSet<>();

        int currentRankIndex = start.getRank().getIndex() + 1;
        int currentFileIndex = start.getFile().getIndex() + 1;
        int targetRankIndex = end.getRank().getIndex();

        while (currentRankIndex < targetRankIndex) {
            positions.add(new Position(File.fromIndex(currentFileIndex), Rank.from(currentRankIndex)));
            currentFileIndex++;
            currentRankIndex++;
        }
        return positions;
    }

    public static Set<Position> getLeftDiagonalMiddlePositions(final Position current, final Position target) {
        Position start = current;
        Position end = target;

        if (current.getRank().getIndex() > target.getRank().getIndex()) {
            start = target;
            end = current;
        }

        final Set<Position> positions = new HashSet<>();

        int currentRankIndex = start.getRank().getIndex() + 1;
        int currentFileIndex = start.getFile().getIndex() - 1;
        int targetRankIndex = end.getRank().getIndex();

        while (currentRankIndex < targetRankIndex) {
            positions.add(new Position(File.fromIndex(currentFileIndex), Rank.from(currentRankIndex)));
            currentFileIndex--;
            currentRankIndex++;
        }
        return positions;
    }
}
