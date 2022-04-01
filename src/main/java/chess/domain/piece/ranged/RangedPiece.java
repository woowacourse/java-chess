package chess.domain.piece.ranged;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.common.CommonPiece;

public abstract class RangedPiece extends CommonPiece {

    protected RangedPiece(PieceType pieceType, Color color) {
        super(pieceType, color);
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
}
