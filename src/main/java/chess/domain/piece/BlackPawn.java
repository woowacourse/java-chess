package chess.domain.piece;

import chess.domain.piece.strategy.Direction;
import java.util.List;

public class BlackPawn extends Pawn {

    public BlackPawn() {
        super(PieceColor.BLACK);
    }

    @Override
    public List<Direction> directions() {
        return Direction.blackPawnDirection();
    }
}
