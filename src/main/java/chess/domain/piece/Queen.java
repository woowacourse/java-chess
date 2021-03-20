package chess.domain.piece;

import static chess.domain.board.Direction.DOWN;
import static chess.domain.board.Direction.LEFT;
import static chess.domain.board.Direction.LEFT_DOWN;
import static chess.domain.board.Direction.LEFT_UP;
import static chess.domain.board.Direction.RIGHT;
import static chess.domain.board.Direction.RIGHT_DOWN;
import static chess.domain.board.Direction.RIGHT_UP;
import static chess.domain.board.Direction.UP;

import chess.domain.board.Direction;
import chess.domain.player.TeamType;
import java.util.Arrays;

public class Queen extends Piece {
    private static final String NAME = "Q";
    private static final double SCORE = 9;

    public Queen(TeamType teamType) {
        super(teamType, NAME, SCORE, Direction.getQueenDirections());
    }
}
