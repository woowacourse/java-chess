package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.piece.condition.MoveCondition;
import chess.exception.ChessPieceMoveNotAllowException;

import java.util.List;

public class Position {

    private final int row;
    private final int column;

    public Position(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    public double calculateGradient(Position position) {
        return (position.row - row) / (double) (position.column - column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Position changePosition(Position target, Board board, ChessPiece piece, List<MoveCondition> moveConditions) {
        moveConditions.stream()
                .filter(moveCondition -> moveCondition.isSatisfiedBy(board, piece, target))
                .findAny()
                .orElseThrow(ChessPieceMoveNotAllowException::new);

        return target;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        final Position position = (Position) o;

        if (row != position.row) return false;
        return column == position.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }

}
