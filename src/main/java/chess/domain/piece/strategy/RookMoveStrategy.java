package chess.domain.piece.strategy;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Team;
import java.util.List;

public final class RookMoveStrategy extends MoveStrategy {

    private static final String NO_MOVE_MESSAGE = "룩이 이동할 수 없는 위치입니다.";

    public static List<Direction> rookDirection(Team team) {
        return Direction.getAbsoluteDirections(team, List.of(
                Direction.TOP, Direction.DOWN, Direction.LEFT, Direction.RIGHT));
    }

    @Override
    public boolean canMove(Team team, Piece targetPiece, Position from, Position to) {
        List<Direction> directions = rookDirection(team);

        if (isInvalidDirection(from, to, directions)) {
            throw new IllegalArgumentException(NO_MOVE_MESSAGE);
        }

        return true;
    }
}
