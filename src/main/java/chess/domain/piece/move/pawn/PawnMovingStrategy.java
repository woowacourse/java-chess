package chess.domain.piece.move.pawn;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.move.MovingStrategy;

public class PawnMovingStrategy implements MovingStrategy {

    private final PawnSupport support;

    public PawnMovingStrategy(Color color) {
        this.support = new PawnSupport(color);
    }

    @Override
    public boolean move(Board board, Point from, Point to) {
        PawnMoveForwardChain chain = new PawnMoveForwardChain(support);
        return chain.move(board, from, to);
    }
}
