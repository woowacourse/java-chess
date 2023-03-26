package chess.domain.score;

import chess.domain.piece.*;

public enum Score {
    QUEEN_SCORE(Queen.class, 9),
    ROOK_SCORE(Rook.class, 5),
    BISHOP_SCORE(Bishop.class, 3),
    KNIGHT_SCORE(Knight.class, 2.5),
    PAWN_SCORE(Pawn.class, 1),
    KING_SCORE(King.class, 0),
    EMPTY_SCORE(Empty.class, 0),
    ;

    private final Class<? extends Piece> pieceClass;
    private final double score;

    Score(Class<? extends Piece> pieceClass, double score) {
        this.pieceClass = pieceClass;
        this.score = score;
    }
}
