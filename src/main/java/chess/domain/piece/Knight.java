package chess.domain.piece;

import chess.domain.piece.strategy.Direction;
import chess.domain.piece.strategy.KnightStrategy;
import java.util.List;

public class Knight extends Piece {

    public Knight(PieceColor pieceColor) {
        super(PieceType.KNIGHT, pieceColor, new KnightStrategy());
    }

    @Override
    public List<Direction> directions() {
        return Direction.knightDirection();
    }
}
