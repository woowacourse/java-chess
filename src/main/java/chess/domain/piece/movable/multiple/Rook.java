package chess.domain.piece.movable.multiple;

import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceName;
import chess.domain.piece.constant.PieceScore;

public final class Rook extends MultipleMovablePiece {

    private static final PieceName PIECE_NAME = PieceName.ROOK;
    private static final PieceScore PIECE_SCORE = PieceScore.ROOK;
    private static final PieceDirections PIECE_DIRECTIONS = PieceDirections.ROOK;
    private static final Rook ROOK = new Rook();

    private Rook() {
        super(PIECE_NAME, PIECE_SCORE, PIECE_DIRECTIONS);
    }

    public static Rook getInstance() {
        return ROOK;
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