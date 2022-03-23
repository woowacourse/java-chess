package chess;

import java.util.Objects;

public class ChessBoardPosition {
    private final ChessBoardColumn column;
    private final ChessBoardRow row;

    public ChessBoardPosition(ChessBoardColumn column, ChessBoardRow row) {
        this.column = column;
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoardPosition that = (ChessBoardPosition) o;
        return column == that.column && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    public ChessBoardPosition flipHorizontally() {
        return new ChessBoardPosition(this.column.flip(), this.row);
    }

    public ChessBoardPosition flipVertically() {
        return new ChessBoardPosition(this.column, this.row.flip());
    }

    public ChessBoardPosition flipDiagonally() {
        return new ChessBoardPosition(this.column.flip(), this.row.flip());
    }
}
