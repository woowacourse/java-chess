package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;
import chess.domain.piece.move.MovingStrategy;
import chess.domain.piece.move.straight.StraightDirection;
import chess.domain.piece.move.straight.InfiniteStepDistance;
import chess.domain.piece.move.straight.StraightMovingStrategy;

public class Queen extends Piece {

    private final MovingStrategy strategy;

    public Queen(Color color) {
        super(color, PieceType.QUEEN);
        this.strategy = new StraightMovingStrategy(StraightDirection.getAll(), InfiniteStepDistance.init());
    }

    @Override
    public boolean move(Route route, EmptyPoints emptyPoints) {
        return strategy.move(route, emptyPoints);
    }
}
