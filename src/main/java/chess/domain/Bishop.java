package chess.domain;

import java.util.List;
import chess.domain.board.Direction;
import chess.domain.board.Position;

public class Bishop extends Piece {

    public Bishop(PieceColor pieceColor, Position position) {
        super(PieceType.BISHOP, pieceColor, position);
    }

    @Override
    public Direction findByDirection(Position from, Position to) {
        final int colum = to.getColumn() - from.getColumn();
        final int row = to.getRow() - from.getRow();

        return Direction.ofDiagonal(colum, row);
    }

    @Override
    protected List<Direction> findByMovableDirection(Piece piece, Direction direction) {
        return Direction.DIAGONAL_DIRECTION;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
