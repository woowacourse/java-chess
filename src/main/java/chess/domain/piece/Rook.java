package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;
import chess.domain.piece.move.straight.InfiniteStepDistance;
import chess.domain.piece.move.straight.StraightDirection;
import chess.domain.piece.move.straight.StraightMovingStrategy;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public boolean move(Route route, EmptyPoints emptyPoints) {
        StraightMovingStrategy strategy =
            new StraightMovingStrategy(StraightDirection.getCross(), InfiniteStepDistance.init());
        return strategy.move(route, emptyPoints);
    }
}

