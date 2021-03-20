package chess.domain.piece;

import chess.domain.piece.strategy.Direction;
import chess.domain.piece.strategy.RookStrategy;
import java.util.List;

public class Rook extends Piece {

    public Rook(PieceColor pieceColor) {
        super(PieceType.ROOK, pieceColor, new RookStrategy());
    }

    public List<Direction> directions() {
        return Direction.straightDirection();
    }
}
