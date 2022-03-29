package chess.domain.piece.strategy;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Team;
import java.util.List;


public final class BishopMoveStrategy extends MoveStrategy {

    private static final String NO_MOVE_MESSAGE = "비숍이 이동할 수 없는 위치입니다.";

    public static List<Direction> bishopDirection(Team team) {
        return Direction.getAbsoluteDirections(
                team, List.of(Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT));
    }

    @Override
    public boolean isValidateCanMove(Team team, Piece targetPiece, Position from, Position to) {
        List<Direction> directions = bishopDirection(team);

        if (isInvalidDirection(from, to, directions)) {
            throw new IllegalArgumentException(NO_MOVE_MESSAGE);
        }

        return true;
    }
}
