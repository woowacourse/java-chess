package chess.domain.piece;

import chess.domain.piece.moveRule.MoveRule;
import chess.domain.piece.moveRule.QueenMoveRule;
import chess.domain.position.Position;

public class Queen extends AbstractPiece {
    private Queen(MoveRule moveRule, Color color) {
        super(moveRule, color);
    }

    public static Queen from(Color color) {
        return new Queen(QueenMoveRule.getInstance(), color);
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
