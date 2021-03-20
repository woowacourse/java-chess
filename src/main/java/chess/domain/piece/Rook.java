package chess.domain.piece;

import chess.domain.board.Direction;

public class Rook extends Piece {
    private static final String NAME = "R";
    private static final double SCORE = 5;

    public Rook(TeamType teamType) {
        super(teamType, NAME, SCORE, Direction.getRookDirections());
    }
}
