package chess.domain.piece;

import chess.domain.piece.strategy.Direction;
import java.util.List;

public class WhitePawn extends Pawn{

    public WhitePawn() {
        super(PieceColor.WHITE);
    }

    @Override
    public List<Direction> directions() {
        return Direction.whitePawnDirection();
    }
}
