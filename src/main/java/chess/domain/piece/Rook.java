package chess.domain.piece;

import chess.domain.result.Score;

public class Rook extends ConsecutiveMovablePiece {
    private static final String NAME = "R";
    private static final double SCORE = 5;

    public Rook(TeamType teamType) {
        super(NAME, teamType, new Score(SCORE), Direction.getRookDirections());
    }
}
