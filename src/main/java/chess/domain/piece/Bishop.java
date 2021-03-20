package chess.domain.piece;

import static chess.domain.board.Direction.LEFT_DOWN;
import static chess.domain.board.Direction.LEFT_UP;
import static chess.domain.board.Direction.RIGHT_DOWN;
import static chess.domain.board.Direction.RIGHT_UP;

import chess.domain.player.TeamType;
import java.util.Arrays;

public class Bishop extends Piece {
    private static final String NAME = "B";
    private static final double SCORE = 3;

    public Bishop(TeamType teamType) {
        super(teamType, NAME, SCORE, Arrays.asList(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN));
    }
}
