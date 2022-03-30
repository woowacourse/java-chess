package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;
import chess.domain.piece.move.MovingStrategy;
import chess.domain.piece.move.straight.StraightDirection;
import chess.domain.piece.move.straight.OneStepDistance;
import chess.domain.piece.move.straight.StraightMovingStrategy;

public class King extends Piece {

    private final MovingStrategy strategy;

    public King(Color color) {
        super(color, PieceType.KING);
        this.strategy = new StraightMovingStrategy(StraightDirection.getAll(), OneStepDistance.init());
    }

    @Override
    public boolean move(Route route, EmptyPoints emptyPoints) {
        return strategy.move(route, emptyPoints);
    }
}
