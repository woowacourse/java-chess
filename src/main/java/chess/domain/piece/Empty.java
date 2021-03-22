package chess.domain.piece;

import chess.domain.piece.strategy.Direction;
import chess.domain.piece.strategy.EmptyStrategy;
import java.util.List;

public class Empty extends Piece {

    public Empty() {
        super(PieceType.EMPTY, PieceColor.NOTHING, new EmptyStrategy());
    }

    @Override
    public List<Direction> directions() {
        return null;
    }
}
