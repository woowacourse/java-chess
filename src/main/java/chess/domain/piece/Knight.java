package chess.domain.piece;

import chess.domain.piece.moveRule.KnightMoveRule;
import chess.domain.piece.moveRule.MoveRule;
import chess.domain.position.Position;

public class Knight extends AbstractPiece {
    private Knight(MoveRule moveRule, Color color) {
        super(moveRule, color);
    }

    public static Knight from(Color color) {
        return new Knight(KnightMoveRule.getInstance(), color);
    }

    @Override
    public Piece move(Position currentPosition, Position nextPosition, Piece pieceOfNextPosition) {
        validateSameColor(pieceOfNextPosition);
        validateMovement(currentPosition, nextPosition);
        return this;
    }

    @Override
    public boolean isSliding() {
        return false;
    }
}
