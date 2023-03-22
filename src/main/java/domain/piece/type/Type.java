package domain.piece.type;

import static domain.piece.Score.BISHOP_SCORE;
import static domain.piece.Score.KNIGHT_SCORE;
import static domain.piece.Score.PAWN_SCORE;
import static domain.piece.Score.QUEEN_SCORE;
import static domain.piece.Score.ROOK_SCORE;
import static domain.piece.Score.ZERO_SCORE;

import domain.piece.Score;

public enum Type {
    KING('k', ZERO_SCORE),
    QUEEN('q', QUEEN_SCORE),
    ROOK('r',ROOK_SCORE),
    BISHOP('b',BISHOP_SCORE),
    KNIGHT('n',KNIGHT_SCORE),
    PAWN('p',PAWN_SCORE),
    EMPTY('.',ZERO_SCORE);

    private final char label;
    private final Score score;

    Type(char label, Score score) {
        this.label = label;
        this.score = score;
    }

    public char getLabel() {
        return label;
    }

    public Score getScore() {
        return score;
    }
}
