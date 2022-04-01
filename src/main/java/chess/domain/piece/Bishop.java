package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;
import chess.domain.piece.move.straight.InfiniteStepDistance;
import chess.domain.piece.move.straight.StraightDirection;
import chess.domain.piece.move.straight.StraightMovingStrategy;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public boolean move(Route route, EmptyPoints emptyPoints) {
        StraightMovingStrategy strategy = new StraightMovingStrategy(StraightDirection.getDiagonal(),
            InfiniteStepDistance.init());
        return strategy.move(route, emptyPoints);
    }
}
