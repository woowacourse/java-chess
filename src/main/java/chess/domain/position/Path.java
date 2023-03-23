package chess.domain.position;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private final List<Position> positions;

    private Path(List<Position> positions) {
        this.positions = List.copyOf(positions);
    }

    public static Path emptyPath() {
        return new Path(List.of());
    }

    public static Path of(final Position startingPosition, final Position endPosition) {
        return new Path(findPassingPositions(startingPosition, endPosition));
    }

    private static List<Position> findPassingPositions(final Position startingPosition, final Position endPosition) {
        if (startingPosition.isInHorizontalPosition(endPosition)) {
            return findHorizontalPassingPositions(startingPosition, endPosition);
        }
        if (startingPosition.isInVerticalPosition(endPosition)) {
            return findVerticalPassingPositions(startingPosition, endPosition);
        }
        if (startingPosition.isInDiagonalPosition(endPosition)) {
            return findDiagonalPassingPositions(startingPosition, endPosition);
        }
        return List.of();
    }

    private static List<Position> findDiagonalPassingPositions(final Position startingPosition, final Position endPosition) {
        final List<File> files = startingPosition.getFile().getFilesTo(endPosition.getFile());
        final List<Rank> ranks = startingPosition.getRank().getRanksTo(endPosition.getRank());
        List<Position> passingPositions = new ArrayList<>();
        for (int index = 0; index < files.size(); index++) {
            passingPositions.add(Position.of(files.get(index), ranks.get(index)));
        }
        return passingPositions;
    }

    private static List<Position> findVerticalPassingPositions(final Position startingPosition, final Position endPosition) {
        List<Position> passingPositions = new ArrayList<>();
        for (Rank rank : startingPosition.getRank().getRanksTo(endPosition.getRank())) {
            passingPositions.add(Position.of(startingPosition.getFile(), rank));
        }
        return passingPositions;
    }

    private static List<Position> findHorizontalPassingPositions(final Position startingPosition, final Position endPosition) {
        List<Position> passingPositions = new ArrayList<>();
        for (File file : startingPosition.getFile().getFilesTo(endPosition.getFile())) {
            passingPositions.add(Position.of(file, startingPosition.getRank()));
        }
        return passingPositions;
    }

    public boolean contains(final Position position) {
        return positions.contains(position);
    }
}
