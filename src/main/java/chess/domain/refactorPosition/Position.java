package chess.domain.refactorPosition;

import chess.domain.board.File;
import chess.domain.board.Rank;
import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(String column, String row) {
        return new Position(File.letterOf(column), Rank.letterOf(row));
    }

    public Position move(int column, int row) {
        return new Position(file.plus(column), rank.plus(row));
    }

    public boolean isMovable(int column, int row) {
        return isFileInRange(column) && isRankInRange(row);
    }

    private boolean isFileInRange(int value) {
        return file.isMoveInRange(file.getNumber() + value);
    }

    private boolean isRankInRange(int value) {
        return rank.isMoveInRange(rank.getNumber() + value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
