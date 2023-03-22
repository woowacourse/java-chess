package chess.domain.piece;

import chess.domain.piece.moveRule.BishopMoveRule;
import chess.domain.piece.moveRule.MoveRule;
import chess.domain.position.Position;

public class Bishop extends AbstractPiece {
    private Bishop(MoveRule moveRule, Color color) {
        super(moveRule, color);
    }

    public static Bishop from(Color color) {
        return new Bishop(BishopMoveRule.getInstance(), color);
    }

    @Override
    public Piece move(Position currentPosition, Position nextPosition, Piece pieceOfNextPosition) {
        validateSameColor(pieceOfNextPosition);
        validateMovement(currentPosition, nextPosition);
        return this;
    }

    @Override
    public boolean isSliding() {
        return true;
    }
}
