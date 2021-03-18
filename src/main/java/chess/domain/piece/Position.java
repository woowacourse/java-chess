package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.piece.condition.MoveCondition;

import java.util.List;
import java.util.Optional;

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

    public Position changePosition(Position target, Board board, Piece piece, List<MoveCondition> moveConditions) {
        Optional<MoveCondition> selectedCondition = moveConditions.stream()
                .filter(moveCondition -> moveCondition.isSatisfyBy(board, piece, target))
                .findAny();

        if (!selectedCondition.isPresent()) {
            throw new IllegalArgumentException("해당 위치로는 이동할 수 없습니다.");
        }

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
