package chess.domain.piece.movable.multiple;

import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceName;
import chess.domain.piece.constant.PieceScore;

public final class Bishop extends MultipleMovablePiece {

    private static final PieceName PIECE_NAME = PieceName.BISHOP;
    private static final PieceScore PIECE_SCORE = PieceScore.BISHOP;
    private static final PieceDirections PIECE_DIRECTIONS = PieceDirections.BISHOP;
    private static final Bishop BISHOP = new Bishop();

    private Bishop() {
        super(PIECE_NAME, PIECE_SCORE, PIECE_DIRECTIONS);
    }

    public static Bishop getInstance() {
        return BISHOP;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
