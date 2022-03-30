package chess.domain.piece.movable.multiple;

import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceName;
import chess.domain.piece.constant.PieceScore;

public final class Queen extends MultipleMovablePiece {

    private static final PieceName PIECE_NAME = PieceName.QUEEN;
    private static final PieceScore PIECE_SCORE = PieceScore.QUEEN;
    private static final PieceDirections PIECE_DIRECTIONS = PieceDirections.QUEEN;
    private static final Queen QUEEN = new Queen();

    private Queen() {
        super(PIECE_NAME, PIECE_SCORE, PIECE_DIRECTIONS);
    }

    public static Queen getInstance() {
        return QUEEN;
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
