package chess.domain.piece;

import chess.domain.result.Score;

public class Knight extends LimitedMovablePiece {
    private static final String NAME = "N";
    private static final double SCORE = 2.5;

    public Knight(TeamType teamType) {
        super(NAME, teamType, new Score(SCORE), Direction.getKnightDirections());
    }
}
