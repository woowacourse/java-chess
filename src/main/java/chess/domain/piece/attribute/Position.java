package chess.domain.piece.attribute;

import static chess.domain.chessboard.Chessboard.isInBoard;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import chess.domain.chessboard.attribute.Direction;
import chess.domain.chessboard.attribute.File;
import chess.domain.chessboard.attribute.Rank;

public class Position {

    private static final Map<String, Position> CACHED_POSITIONS = new HashMap<>();

    private static final String PATTERN = "^[a-h][1-8]$";

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position from(final String position) {
        if (!position.matches(PATTERN)) {
            throw new IllegalArgumentException(
                    "잘못된 형식입니다. 파일은 a~h, 랭크는 1~8입니다. (예: a8, f4): %s".formatted(position));
        }
        return of(File.from(position.charAt(0)), Rank.from(position.charAt(1)));
    }

    public static Position of(final Rank rank, final File file) {
        return of(file, rank);
    }

    public static Position of(final File file, final Rank rank) {
        if (CACHED_POSITIONS.isEmpty()) {
            initializePositions();
        }
        return CACHED_POSITIONS.get(keyOf(file, rank));
    }

    private static void initializePositions() {
        for (final Rank rank : Rank.values()) {
            putPositions(rank);
        }
    }

    private static void putPositions(final Rank rank) {
        for (final File file : File.values()) {
            CACHED_POSITIONS.put(keyOf(file, rank), new Position(file, rank));
        }
    }

    private static String keyOf(final File file, final Rank rank) {
        return file.name() + rank.name();
    }

    public Optional<Position> moveTo(final Direction direction) {
        int row = rank.toRow() + direction.row();
        int column = file.toColumn() + direction.column();
        if (isInBoard(column, row)) {
            return Optional.of(Position.of(File.from(column), Rank.from(row)));
        }
        return Optional.empty();
    }

    public File file() {
        return file;
    }

    public Rank rank() {
        return rank;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        return object instanceof Position other
                && rank == other.rank
                && file == other.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
