package chess.domain.piece;

import static chess.domain.board.Direction.DOWN;
import static chess.domain.board.Direction.LEFT;
import static chess.domain.board.Direction.LEFT_DOWN;
import static chess.domain.board.Direction.LEFT_UP;
import static chess.domain.board.Direction.RIGHT;
import static chess.domain.board.Direction.RIGHT_DOWN;
import static chess.domain.board.Direction.RIGHT_UP;
import static chess.domain.board.Direction.UP;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;
import chess.domain.player.TeamType;
import java.util.Arrays;
import java.util.List;

public class Queen extends Piece {
    private static final String NAME = "Q";

    public Queen(TeamType teamType) {
        super(teamType, NAME);
    }

    @Override
    public void move(Board board, Coordinate currentCoordinate, Coordinate targetCoordinate) {
        List<Direction> directions = Arrays.asList(LEFT, RIGHT, UP, DOWN, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN);
        Direction moveCommandDirection = currentCoordinate.calculateDirection(targetCoordinate);
        if (!directions.contains(moveCommandDirection)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
        Coordinate movingCoordinate = currentCoordinate.move(moveCommandDirection);
        while(!movingCoordinate.equals(targetCoordinate)) {
            Piece piece = board.find(movingCoordinate);
            if (piece != null) {
                throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
            }
            movingCoordinate = movingCoordinate.move(moveCommandDirection);
        }
        Piece piece = board.find(movingCoordinate);
        if (piece != null && piece.isTeamOf(this.getTeamType())) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        board.put(board.find(currentCoordinate), targetCoordinate);
        board.remove(currentCoordinate);
    }
}
