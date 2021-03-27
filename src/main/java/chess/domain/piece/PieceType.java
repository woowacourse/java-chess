package chess.domain.piece;

import chess.domain.result.Score;

public enum PieceType {
    EMPTY(".", 0),
    PAWN("p", 1),
    ROOK("r", 5),
    KNIGHT("n", 2.5),
    BISHOP("b", 3),
    QUEEN("q", 9),
    KING("k", 0);

    private static final String INVALID_TYPE_ERROR_MESSAGE = "빈 칸은 변환할 수 없습니다.";
    private final String type;
    private final Score score;

    PieceType(String type, double score) {
        this(type, new Score(score));
    }

    PieceType(String type, Score score) {
        this.type = type;
        this.score = score;
    }

    public String toBlack() {
        if (this.equals(EMPTY)) {
            throw new IllegalArgumentException(INVALID_TYPE_ERROR_MESSAGE);
        }
        return type.toUpperCase();
    }

    public boolean is(PieceType type) {
        return this.equals(type);
    }

    public String getType() {
        return type;
    }

    public Score getScore() {
        return score;
    }
}
