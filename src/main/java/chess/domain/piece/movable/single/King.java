package chess.domain.piece.movable.single;

import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceName;
import chess.domain.piece.constant.PieceScore;

public final class King extends SingleMovablePiece {

    private static final PieceName PIECE_NAME = PieceName.KING;
    private static final PieceScore PIECE_SCORE = PieceScore.KING;
    private static final PieceDirections PIECE_DIRECTIONS = PieceDirections.KING;
    private static final King KING = new King();

    private King() {
        super(PIECE_NAME, PIECE_SCORE, PIECE_DIRECTIONS);
    }

    public static King getInstance() {
        return KING;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
