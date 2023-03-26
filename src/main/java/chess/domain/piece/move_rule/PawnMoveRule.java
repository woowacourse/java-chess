package chess.domain.piece.move_rule;

import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

public abstract class PawnMoveRule implements MoveRule {
    private static final PawnMoveRule blackInstance = new BlackPawnMoveRule();
    private static final PawnMoveRule whiteInstance = new WhitePawnMoveRule();

    public static PawnMoveRule getInstance(Color color) {
        if (color == Color.BLACK) {
            return blackInstance;
        }
        return whiteInstance;
    }

    @Override
    public PieceType pieceType() {
        return PieceType.PAWN;
    }

    @Override
    public boolean isPawnMove() {
        return true;
    }

    @Override
    public boolean isKingMove() {
        return false;
    }
}
