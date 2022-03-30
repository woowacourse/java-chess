package chess.domain.piece.movable.single;

import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceName;
import chess.domain.piece.constant.PieceScore;

public final class Knight extends SingleMovablePiece {

    private static final PieceName PIECE_NAME = PieceName.KNIGHT;
    private static final PieceScore PIECE_SCORE = PieceScore.KNIGHT;
    private static final PieceDirections PIECE_DIRECTIONS = PieceDirections.KNIGHT;
    private static final Knight KNIGHT = new Knight();

    private Knight() {
        super(PIECE_NAME, PIECE_SCORE, PIECE_DIRECTIONS);
    }

    public static Knight getInstance() {
        return KNIGHT;
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
