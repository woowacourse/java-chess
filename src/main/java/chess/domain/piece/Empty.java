package chess.domain.piece;

import chess.domain.piece.strategy.Direction;
import chess.domain.piece.strategy.PawnStrategy;
import java.util.List;

public class Empty extends Piece {

    public Empty() {
        super(PieceType.EMPTY, PieceColor.NOTHING, new PawnStrategy());
    }

    @Override
    public List<Direction> directions() {
        return null;
    }
}
