package chess.domain.score;

import chess.domain.piece.*;

import java.util.Arrays;

public enum Score {
    QUEEN_SCORE(Queen.class, 9),
    ROOK_SCORE(Rook.class, 5),
    BISHOP_SCORE(Bishop.class, 3),
    KNIGHT_SCORE(Knight.class, 2.5),
    PAWN_SCORE(Pawn.class, 1),
    KING_SCORE(King.class, 0),
    EMPTY_SCORE(Empty.class, 0),
    ;

    public static final String NO_MATCHED_SCORE_MESSAGE = "점수를 알 수 없습니다.";
    private final Class<? extends Piece> pieceClass;
    private final double score;

    Score(Class<? extends Piece> pieceClass, double score) {
        this.pieceClass = pieceClass;
        this.score = score;
    }

    public static double findScoreBy(Piece piece) {
        return Arrays.stream(values())
                .filter(it -> it.pieceClass.equals(piece.getClass()))
                .map(it -> it.score)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCHED_SCORE_MESSAGE));
    }
}
