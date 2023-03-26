package domain.piece.type;

import static domain.piece.score.Score.BISHOP_SCORE;
import static domain.piece.score.Score.KNIGHT_SCORE;
import static domain.piece.score.Score.PAWN_SCORE;
import static domain.piece.score.Score.QUEEN_SCORE;
import static domain.piece.score.Score.ROOK_SCORE;
import static domain.piece.score.Score.ZERO_SCORE;

import java.util.Arrays;

import domain.piece.score.Score;

public enum PieceType {
    KING('k', ZERO_SCORE),
    QUEEN('q', QUEEN_SCORE),
    ROOK('r', ROOK_SCORE),
    BISHOP('b', BISHOP_SCORE),
    KNIGHT('n', KNIGHT_SCORE),
    PAWN('p', PAWN_SCORE),
    EMPTY('.', ZERO_SCORE);

    private final char label;
    private final Score score;

    PieceType(char label, Score score) {
        this.label = label;
        this.score = score;
    }

    public char getLabel() {
        return label;
    }

    public Score getScore() {
        return score;
    }

    public static PieceType find(String piece) {
        return Arrays.stream(PieceType.values())
                .filter(type -> type.name().equals(piece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물 입니다."));
    }
}
