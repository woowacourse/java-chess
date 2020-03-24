package chess.domain;

import chess.domain.score.PawnScoring;

import java.util.function.Function;

public enum PieceType {
    PAWN(new PawnScoring());

    private Function<Boolean, Double> scoring;

    PieceType(Function<Boolean, Double> scoring) {
        this.scoring = scoring;
    }

    public double score(boolean mustChange) {
        return this.scoring.apply(mustChange);
    }
}
