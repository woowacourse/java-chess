package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;
import chess.domain.piece.move.straight.InfiniteStepDistance;
import chess.domain.piece.move.straight.StraightDirection;
import chess.domain.piece.move.straight.StraightMovingStrategy;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public boolean move(Route route, EmptyPoints emptyPoints) {
        StraightMovingStrategy strategy =
            new StraightMovingStrategy(StraightDirection.getAll(), InfiniteStepDistance.init());
        return strategy.move(route, emptyPoints);
    }
}
