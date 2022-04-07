package chess.domain.piece.pawn;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.PieceRule;

public final class Pawn implements PieceRule {

    private static final String PAWN_INITIAL_NAME = "P";
    private static final String PAWN_NAME = "pawn";
    private static final double PAWN_SCORE = 1;

    private final PawnMoveRule pawnMoveRule;

    private Pawn(PawnMoveRule pawnMoveRule) {
        this.pawnMoveRule = pawnMoveRule;
    }

    public Pawn(Color color) {
        this(PawnMoveRule.from(color));
    }

    @Override
    public PieceRule move(Position source, Position target, ChessBoard chessboard) {
        if (pawnMoveRule.isMovable(source, target, chessboard)) {
            return this;
        }
        throw new IllegalStateException("움직일 수 없는 곳입니다.");
    }

    @Override
    public String convertedName(Color color) {
        return color.convertToCase(PAWN_INITIAL_NAME);
    }

    @Override
    public double score() {
        return PAWN_SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public String name() {
        return PAWN_NAME;
    }
}
