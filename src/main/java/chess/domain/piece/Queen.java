package chess.domain.piece;

import chess.domain.result.Score;

public class Queen extends ConsecutiveMovablePiece {
    private static final String NAME = "Q";
    private static final double SCORE = 9;

    public Queen(TeamType teamType) {
        super(NAME, teamType, new Score(SCORE), Direction.getQueenDirections());
    }
}
