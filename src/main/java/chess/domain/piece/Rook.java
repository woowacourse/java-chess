package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.move.MovingStrategy;
import chess.domain.piece.move.straight.StraightDirection;
import chess.domain.piece.move.straight.InfiniteStepDistance;
import chess.domain.piece.move.straight.StraightMovingStrategy;

public class Rook extends Piece {

    private final MovingStrategy strategy;

    public Rook(Color color) {
        super(color, PieceType.ROOK);
        this.strategy = new StraightMovingStrategy(StraightDirection.getCross(), InfiniteStepDistance.init());
    }

    @Override
    public boolean move(Board board, Point from, Point to) {
        return strategy.move(board, from, to);
    }
}

