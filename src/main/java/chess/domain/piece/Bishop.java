package chess.domain.piece;

import chess.domain.result.Score;

public class Bishop extends ConsecutiveMovablePiece {
    private static final String NAME = "B";
    private static final double SCORE = 3;

    public Bishop(TeamType teamType) {
        super(NAME, teamType, new Score(SCORE), Direction.getBishopDirections());
    }
}
