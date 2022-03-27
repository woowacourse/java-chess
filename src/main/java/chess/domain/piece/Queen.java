package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Queen extends Piece {

    private static final String name = "Q";
    private static final float score = 9.0f;
    private static final List<Direction> directions = Direction.pullAllBasicDirections();

    public Queen(Team team) {
        super(name, score, team);
    }

    @Override
    public void movable(Position from, Position to) {
        if (!isMovablePath(from, to)) {
            throw new IllegalArgumentException("Queen 이 움직일 수 있는 방향이 아닙니다.");
        }
    }

    @Override
    public Direction findDirection(Position from, Position to) {
        return from.findDirection(to, false);
    }

    @Override
    public boolean isMovablePath(Position from, Position to) {
        Direction direction = from.findDirection(to, false);
        return directions.contains(direction);
    }
}