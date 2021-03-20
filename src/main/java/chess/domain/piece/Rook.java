package chess.domain.piece;

import static chess.domain.board.Direction.DOWN;
import static chess.domain.board.Direction.LEFT;
import static chess.domain.board.Direction.RIGHT;
import static chess.domain.board.Direction.UP;

import chess.domain.board.Direction;
import chess.domain.player.TeamType;
import java.util.Arrays;

public class Rook extends Piece {
    private static final String NAME = "R";
    private static final double SCORE = 5;

    public Rook(TeamType teamType) {
        super(teamType, NAME, SCORE, Direction.getRookDirections());
    }
}
