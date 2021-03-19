package chess.domain.piece;

import chess.domain.piece.strategy.BishopStrategy;
import chess.domain.piece.strategy.Direction;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(PieceColor pieceColor) {
        super(PieceType.BISHOP, pieceColor, new BishopStrategy());
    }

    @Override
    public List<Direction> directions() {
        return Direction.diagonalDirection();
    }
}
