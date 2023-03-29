package chess.domain.piece.move_rule;

import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

public abstract class PawnMoveRule implements MoveRule {

    public static PawnMoveRule of(Color color) {
        if (color == Color.BLACK) {
            return new BlackPawnMoveRule();
        }
        return new WhitePawnMoveRule();
    }

    @Override
    public PieceType pieceType() {
        return PieceType.PAWN;
    }

    @Override
    public boolean isPawnMove() {
        return true;
    }
}
