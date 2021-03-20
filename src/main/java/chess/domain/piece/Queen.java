package chess.domain.piece;

import chess.domain.board.Direction;

public class Queen extends Piece {
    private static final String NAME = "Q";
    private static final double SCORE = 9;

    public Queen(TeamType teamType) {
        super(teamType, NAME, SCORE, Direction.getQueenDirections());
    }
}
