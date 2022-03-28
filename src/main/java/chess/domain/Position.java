package chess.domain;

import chess.domain.player.Team;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Position {

    private static final int MIN_RANK_RANGE = 1;
    private static final int MAX_RANK_RANGE = 8;
    private static final char MIN_FILE_RANGE = 'a';
    private static final char MAX_FILE_RANGE = 'h';
    private static final int WHITE_PAWN_FIRST_RANK = 2;
    private static final int BLACK_PAWN_FIRST_RANK = 7;
    private static final int KNIGHT_MOVE_DISTANCE = 3;

    private final int rank;
    private final char file;

    public Position(final int rank, final char file) {
        char lowerCaseFile = Character.toLowerCase(file);
        validatePositionRange(rank, lowerCaseFile);
        this.rank = rank;
        this.file = lowerCaseFile;
    }

    public Position(String position) {
        this(Character.getNumericValue(position.charAt(1)), position.charAt(0));
    }

    private void validatePositionRange(final int rank, final char file) {
        if (rank < MIN_RANK_RANGE || rank > MAX_RANK_RANGE) {
            throw new IllegalArgumentException("잘못된 범위입니다.");
        }
        if (file < MIN_FILE_RANGE || file > MAX_FILE_RANGE) {
            throw new IllegalArgumentException("잘못된 범위입니다.");
        }
    }

    public boolean isFirstTurnOfPawn() {
        return rank == WHITE_PAWN_FIRST_RANK || rank == BLACK_PAWN_FIRST_RANK;
    }

    public List<Position> findAllBetweenPosition(final Position destination) {
        if (isMoveOfKnight(destination)) {
            return Collections.emptyList();
        }
        if (isMoveLinear(destination)) {
            return findBetweenLinearPosition(destination);
        }
        if (isMoveDiagonal(destination)) {
            return findBetweenDiagonalPosition(destination);
        }
        throw new IllegalArgumentException("올바른 이동이 아닙니다.");
    }

    public boolean isMoveLinear(final Position destination) {
        final int fileDistance = Math.abs(file - destination.file);
        final int rankDistance = Math.abs(rank - destination.rank);
        return fileDistance == 0 && rankDistance > 0 || fileDistance > 0 && rankDistance == 0;
    }

    public boolean isMoveDiagonal(final Position destination) {
        final int fileDistance = Math.abs(file - destination.file);
        final int rankDistance = Math.abs(rank - destination.rank);
        return fileDistance + rankDistance != 0 && fileDistance == rankDistance;
    }

    public boolean isMoveOfKnight(final Position destination) {
        final int fileDistance = Math.abs(file - destination.file);
        final int rankDistance = Math.abs(rank - destination.rank);
        if (fileDistance + rankDistance != KNIGHT_MOVE_DISTANCE) {
            return false;
        }
        return fileDistance != 0 && rankDistance != 0;
    }

    public boolean isMoveForward(final Position destination, final Team team) {
        if (file != destination.file) {
            return false;
        }
        if (team == Team.BLACK) {
            return rank - destination.rank > 0;
        }
        return destination.rank - rank > 0;
    }

    public boolean isMoveDiagonalForward(final Position destination, final Team team) {
        final int fileDistance = Math.abs(file - destination.file);
        final int rankDistance = destination.rank - rank;
        if (team == Team.BLACK) {
            return fileDistance > 0 && fileDistance == Math.abs(rankDistance);
        }
        return fileDistance > 0 && fileDistance == rankDistance;
    }

    public int calculateDistance(final Position destination) {
        final int fileDistance = Math.abs(file - destination.file);
        final int rankDistance = Math.abs(rank - destination.rank);
        return fileDistance + rankDistance;
    }

    public boolean isSameFile(final char file) {
        return this.file == file;
    }

    private List<Position> findBetweenLinearPosition(final Position destination) {
        final List<Position> positions = new ArrayList<>();

        final int startRank = Math.min(rank, destination.rank);
        final int startFile = Math.min(file, destination.file);
        final int endRank = Math.max(rank, destination.rank);
        final int endFile = Math.max(file, destination.file);

        addBetweenVerticalPosition(positions, startRank, endRank);
        addBetweenHorizonPosition(positions, startFile, endFile);
        return positions;
    }

    private void addBetweenVerticalPosition(final List<Position> positions, final int startRank, final int endRank) {
        for (int i = startRank + 1; i < endRank; i++) {
            positions.add(new Position(i, file));
        }
    }

    private void addBetweenHorizonPosition(final List<Position> positions, final int startFile, final int endFile) {
        for (int i = startFile + 1; i < endFile; i++) {
            positions.add(new Position(rank, (char) i));
        }
    }

    private List<Position> findBetweenDiagonalPosition(final Position destination) {
        final List<Position> positions = new ArrayList<>();

        final int startRank = Math.min(rank, destination.rank);
        final int startFile = Math.min(file, destination.file);
        final int end = Math.max(rank, destination.rank);

        addBetweenDiagonalPosition(positions, startRank, startFile, end);
        return positions;
    }

    private void addBetweenDiagonalPosition(final List<Position> positions,
            final int startRank, final int startFile, final int end) {
        for (int i = startRank + 1, j = startFile + 1; i < end; i++, j++) {
            positions.add(new Position(i, (char) j));
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

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file - 'a';
    }
}
