package chess.model.piece;

import chess.model.Type;

public enum DefaultType implements Type {

    EMPTY(0);

    private final double score;

    DefaultType(final double score) {
        this.score = score;
    }

    @Override
    public double getScore() {
        return score;
    }
}
