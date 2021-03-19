package chess.domain.piece;

import chess.domain.piece.strategy.Direction;
import chess.domain.piece.strategy.KingStrategy;
import java.util.List;

public class King extends Piece {

    public King(PieceColor pieceColor) {
        super(PieceType.KING, pieceColor, new KingStrategy());
    }

    @Override
    public List<Direction> directions() {
        return Direction.everyDirection();
    }
}
