package chess.domain.piece;

import chess.domain.piece.moveRule.KingMoveRule;
import chess.domain.piece.moveRule.MoveRule;
import chess.domain.position.Position;

public class King extends AbstractPiece {
    private King(MoveRule moveRule, Color color) {
        super(moveRule, color);
    }

    public static King from(Color color) {
        return new King(KingMoveRule.getInstance(), color);
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

    @Override
    public boolean isKing() {
        return true;
    }
}
