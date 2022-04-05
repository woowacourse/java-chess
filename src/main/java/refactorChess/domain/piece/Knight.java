package refactorChess.domain.piece;

import java.util.List;
import refactorChess.domain.board.Direction;
import refactorChess.domain.board.Position;

public class Knight extends Piece {

    public Knight(PieceColor pieceColor, Position position) {
        super(PieceType.KNIGHT, pieceColor, position);
    }

    @Override
    protected Direction findByDirection(Position from, Position to) {
        final int column = to.getColumn() - from.getColumn();
        final int row = to.getRow() - from.getRow();

        return Direction.of(column, row);
    }

    @Override
    protected List<Direction> findByMovableDirection(Piece piece, Direction direction) {
        return Direction.KNIGHT_DIRECTION;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
