package chess.domain;

import chess.domain.player.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {

    private static final List<Position> CACHE = new ArrayList<>();

    private static final int POSITION_FILE_INDEX = 0;
    private static final int POSITION_RANK_INDEX = 1;
    private static final int MIN_RANK_RANGE = 1;
    private static final int MAX_RANK_RANGE = 8;
    private static final char MIN_FILE_RANGE = 'a';
    private static final char MAX_FILE_RANGE = 'h';

    private static final int WHITE_PAWN_FIRST_RANK = 2;
    private static final int BLACK_PAWN_FIRST_RANK = 7;

    static {
        for (int rank = MIN_RANK_RANGE; rank <= MAX_RANK_RANGE; rank++) {
            addCachePosition(rank);
        }
    }

    private final int rank;
    private final char file;

    private Position(final int rank, final char file) {
        this.rank = rank;
        this.file = file;
    }

    private static void addCachePosition(int rank) {
        for (char file = MIN_FILE_RANGE; file <= MAX_FILE_RANGE; file++) {
            CACHE.add(new Position(rank, file));
        }
    }

    public static Position of(final int rank, final char file) {
        return CACHE.stream()
                .filter(it -> it.rank == rank && it.file == Character.toLowerCase(file))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 범위입니다."));
    }

    public static Position of(final String position) {
        return CACHE.stream()
                .filter(it -> it.rank == Character.getNumericValue(position.charAt(POSITION_RANK_INDEX)) &&
                        it.file == Character.toLowerCase(position.charAt(POSITION_FILE_INDEX)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 범위입니다."));
    }

    public boolean isMoveLinear(final Position destination) {
        return Direction.of(this, destination).isMoveLinear();
    }

    public boolean isMoveDiagonal(final Position destination) {
        return Direction.of(this, destination).isMoveDiagonal();
    }

    public boolean isMoveOfKnight(final Position destination) {
        return Direction.of(this, destination).isMoveOfKnight();
    }

    public boolean isMoveForward(final Position destination, final Team team) {
        return Direction.of(this, destination).isMoveForward(team);
    }

    public boolean isMoveDiagonalForward(final Position destination, final Team team) {
        return Direction.of(this, destination).isMoveDiagonalForward(team);
    }

    public boolean isFirstTurnOfPawn() {
        return rank == WHITE_PAWN_FIRST_RANK || rank == BLACK_PAWN_FIRST_RANK;
    }

    public int calculateDistance(final Position destination) {
        final int fileDistance = Math.abs(destination.file - file);
        final int rankDistance = Math.abs(destination.rank - rank);
        return fileDistance + rankDistance;
    }

    public boolean isSameFile(final char file) {
        return this.file == file;
    }

    public List<Position> findAllBetweenPosition(final Position destination) {
        final List<Position> positions = new ArrayList<>();
        final Direction direction = Direction.of(this, destination);
        Position position = proceed(direction);
        while (position != destination) {
            positions.add(position);
            position = position.proceed(direction);
        }
        return positions;
    }

    private Position proceed(final Direction direction) {
        return Position.of(rank + direction.getRank(), (char) (file + direction.getFile()));
    }

    public int findRankDistance(final Position destinationPosition) {
        return destinationPosition.rank - rank;
    }

    public int findFileDistance(final Position destinationPosition) {
        return destinationPosition.file - file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
