package chess.domain.piece;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.attribute.Team;
import java.util.ArrayList;
import java.util.List;

public final class Knight extends AbstractPiece {
    private static final String NO_MOVE_MESSAGE = "나이트가 이동할 수 없는 위치입니다.";

    public Knight(Team team) {
        super(new Name("N"), team);
    }

    @Override
    public boolean canMove(Piece targetPiece, Position from, Position to) {
        List<Direction> directions = knightDirection();
        if (Direction.isInvalidDistance(from, to, directions)) {
            throw new IllegalArgumentException(NO_MOVE_MESSAGE);
        }
        return true;
    }

    public List<Direction> knightDirection() {
        return Direction.getAbsoluteDirections(team,
                List.of(Direction.TOP_TOP_RIGHT, Direction.RIGHT_RIGHT_TOP, Direction.RIGHT_RIGHT_DOWN,
                        Direction.DOWN_DOWN_RIGHT,
                        Direction.DOWN_DOWN_LEFT, Direction.LEFT_LEFT_DOWN, Direction.LEFT_LEFT_TOP,
                        Direction.TOP_TOP_LEFT));
    }

    @Override
    public List<Position> calculateRoute(Position from, Position to) {
        return new ArrayList<>();
    }

}
