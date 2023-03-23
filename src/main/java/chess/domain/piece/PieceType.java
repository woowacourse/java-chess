package chess.domain.piece;

import chess.domain.Score;

public enum PieceType {
    KING("k", Score.ZERO),
    QUEEN("q", new Score(9.0)),
    KNIGHT("n", new Score(2.5)),
    BISHOP("b", new Score(3.0)),
    ROOK("r", new Score(5.0)),
    PAWN("p", new Score(1.0)),
    NOPIECE(".", Score.ZERO);

    private final String symbol;
    private final Score score;

    PieceType(final String symbol, final Score score) {
        this.symbol = symbol;
        this.score = score;
    }

    public Score calculateTotalScore(Long count){
        return score.multiply(count);
    }

    public String getSymbol() {
        return symbol;
    }
}
