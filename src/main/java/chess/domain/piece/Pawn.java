package chess.domain.piece;

import chess.domain.board.Position;

public class Pawn extends Piece {

    private final MoveStrategy strategy;

    public Pawn(Color color) {
        super(color);
        this.strategy = PawnMoveStrategy.of(color);
    }

    public boolean canMove(Position src, Position dest) {
        return strategy.canMove(src, dest);
    }
}
