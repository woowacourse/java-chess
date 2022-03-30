package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;
import chess.domain.piece.move.straight.OneStepDistance;
import chess.domain.piece.move.straight.StraightDirection;
import chess.domain.piece.move.straight.StraightMovingStrategy;

public class King extends Piece {

    public King(Color color) {
        super(color, PieceType.KING);
    }

    @Override
    public boolean move(Route route, EmptyPoints emptyPoints) {
        StraightMovingStrategy strategy = new StraightMovingStrategy(StraightDirection.getAll(),
            OneStepDistance.init());
        return strategy.move(route, emptyPoints);
    }
}
