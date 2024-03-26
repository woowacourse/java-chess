package chess.domain.piece.attribute;

import static chess.domain.chessboard.Chessboard.isInBoard;

import java.util.Objects;
import java.util.Optional;

import chess.domain.chessboard.attribute.Direction;
import chess.domain.chessboard.attribute.File;
import chess.domain.chessboard.attribute.Rank;

public class Position {

    private static final String PATTERN = "^[a-h][1-8]$";

    private final File file;
    private final Rank rank;

    protected Position(final File file, final Rank rank) {
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
        return Positions.get(file, rank);
    }

    public Optional<Position> moveTo(final Direction direction) {
        int row = rank.toRow() + direction.row();
        int column = file.toColumn() + direction.column();
        if (isInBoard(column, row)) {
            return Optional.of(Position.of(File.from(column), Rank.from(row)));
        }
        return Optional.empty();
    }

    public Optional<Position> after(final Movement movement) {
        Optional<Position> position = Optional.of(this);
        for (Direction direction : movement.directions()) {
            position = position.flatMap(presentPosition -> presentPosition.moveTo(direction));
        }
        return position;
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

    @Override
    public String toString() {
        return file.name() + rank.toString();
    }
}
