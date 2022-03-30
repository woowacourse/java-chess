package chess.domain.piece.ranged;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.ArrayList;
import java.util.List;

public abstract class RangedPiece extends Piece {

    protected RangedPiece(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    public List<Position> calculatePathToValidate(final Position current, final Position target,
                                                  final Piece targetPiece) {
        Direction direction = findValidDirection(current, target);
        List<Position> path = new ArrayList<>();
        Position moved = current.move(direction);
        while (!moved.equals(target)) {
            path.add(moved);
            moved = moved.move(direction);
        }
        if (hasSameColor(targetPiece)) {
            path.add(target);
        }
        return path;
    }

    @Override
    protected Direction findValidDirection(Position current, Position target) {
        final int columnDifference = target.calculateColumnDifference(current);
        final int rowDifference = target.calculateRowDifference(current);
        final Direction direction = Direction.calculate(columnDifference, rowDifference);
        validateDirection(direction);
        validateRange(columnDifference, rowDifference);
        return direction;
    }

    protected abstract void validateDirection(Direction direction);

    protected abstract void validateRange(int columnDifference, int rowDifference);

    @Override
    public boolean isEmpty() {
        return false;
    }
}
