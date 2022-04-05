package refactorChess.domain.piece;

import java.util.List;
import refactorChess.domain.board.Direction;
import refactorChess.domain.board.Position;

public class King extends Piece {

    public King(PieceColor pieceColor, Position position) {
        super(PieceType.KING, pieceColor, position);
    }

    @Override
    protected Direction findByDirection(Position from, Position to) {
        final int column = to.getColumn() - from.getColumn();
        final int row = to.getRow() - from.getRow();

        return Direction.ofAll(column, row);
    }

    @Override
    protected List<Direction> findByMovableDirection(Piece piece, Direction direction) {
        return Direction.EVERY_DIRECTION;
    }
}
