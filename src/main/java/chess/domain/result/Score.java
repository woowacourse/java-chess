package chess.domain.result;

import chess.domain.piece.*;

import java.util.Arrays;

public enum Score {
    QUEEN_SCORE(PieceType.QUEEN, 9),
    ROOK_SCORE(PieceType.ROOK, 5),
    BISHOP_SCORE(PieceType.BISHOP, 3),
    KNIGHT_SCORE(PieceType.KNIGHT, 2.5),
    PAWN_SCORE(PieceType.PAWN, 1),
    KING_SCORE(PieceType.KING, 0),
    EMPTY_SCORE(PieceType.EMPTY, 0),
    ;

    public static final String NO_MATCHED_SCORE_MESSAGE = "점수를 알 수 없습니다.";
    private final PieceType pieceType;
    private final double score;

    Score(PieceType pieceType, double score) {
        this.pieceType = pieceType;
        this.score = score;
    }

    public static double findScoreBy(Piece piece) {
        return Arrays.stream(values())
                .filter(it -> it.pieceType == piece.getPieceType())
                .map(it -> it.score)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCHED_SCORE_MESSAGE));
    }
}
