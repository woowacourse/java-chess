package chess.controller.console;

import chess.domain.piece.Owner;

public class ScoreDto {
    private final Owner owner;
    private final double score;

    public ScoreDto(final Owner owner, final double score) {
        this.owner = owner;
        this.score = score;
    }

    public Owner owner() {
        return owner;
    }

    public double score() {
        return score;
    }
}
