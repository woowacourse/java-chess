package chess.domain.position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Position {

    private final Rank rank;
    private final File file;

    public Position(final int rank, final char file) {
        this.rank = Rank.from(rank);
        this.file = File.from(file);
    }

    public Position(String position) {
        this(Character.getNumericValue(position.charAt(1)), position.charAt(0));
    }

    public boolean isFirstTurnOfPawn() {
        return rank.isFirstTurnOfPawn();
    }

    public boolean isSameFile(final File file) {
        return this.file == file;
    }

    public int calculateDistance(final Position destination) {
        final int fileDistance = file.calculateFileInAbsolute(destination.file);
        final int rankDistance = rank.calculateRankInAbsolute(destination.rank);
        return fileDistance + rankDistance;
    }

    public List<Position> findAllBetweenPosition(final Position destination) {
        if (MoveChecker.isForKnight(this, destination)) {
            return Collections.emptyList();
        }
        if (MoveChecker.isLinear(this, destination)) {
            return findBetweenLinearPosition(destination);
        }
        if (MoveChecker.isDiagonal(this, destination)) {
            return findBetweenDiagonalPosition(destination);
        }
        throw new IllegalArgumentException("올바른 이동이 아닙니다.");
    }

    private List<Position> findBetweenLinearPosition(final Position destination) {
        final List<Position> positions = new ArrayList<>();

        final int startRank = Math.min(rank.getValue(), destination.rank.getValue());
        final int startFile = Math.min(file.getValue(), destination.file.getValue());
        final int endRank = Math.max(rank.getValue(), destination.rank.getValue());
        final int endFile = Math.max(file.getValue(), destination.file.getValue());

        addBetweenVerticalPosition(positions, startRank, endRank);
        addBetweenHorizonPosition(positions, startFile, endFile);
        return positions;
    }

    private void addBetweenVerticalPosition(final List<Position> positions, final int startRank, final int endRank) {
        final char file = this.file.getValue();
        for (int rank = startRank + 1; rank < endRank; rank++) {
            positions.add(new Position(rank, file));
        }
    }

    private void addBetweenHorizonPosition(final List<Position> positions, final int startFile, final int endFile) {
        final int rank = this.rank.getValue();
        for (int file = startFile + 1; file < endFile; file++) {
            positions.add(new Position(rank, (char) file));
        }
    }

    private List<Position> findBetweenDiagonalPosition(final Position destination) {
        final List<Position> positions = new ArrayList<>();

        final int startRank = Math.min(rank.getValue(), destination.rank.getValue());
        final int startFile = Math.min(file.getValue(), destination.file.getValue());
        final int end = Math.max(rank.getValue(), destination.rank.getValue());

        addBetweenDiagonalPosition(positions, startRank, startFile, end);
        return positions;
    }

    private void addBetweenDiagonalPosition(final List<Position> positions,
            final int startRank, final int startFile, final int end) {
        for (int rank = startRank + 1, file = startFile + 1; rank < end; rank++, file++) {
            positions.add(new Position(rank, (char) file));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    public Rank getRank() {
        return rank;
    }

    public File getFile() {
        return file;
    }
}
