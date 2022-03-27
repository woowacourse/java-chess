package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Knight extends Piece {

    private static final String name = "N";
    private static final float score = 2.5f;
    private static final List<Direction> directions = Direction.pullKnightDirections();

    public Knight(Team team) {
        super(name, score, team);
    }

    @Override
    public void movable(Position from, Position to) {
        if (!isMovablePath(from, to)) {
            throw new IllegalArgumentException("Knight 이 움직일 수 있는 방향이 아닙니다.");
        }
    }

    @Override
    public Direction findDirection(Position from, Position to) {
        return from.findDirection(to, true);
    }

    @Override
    public boolean isMovablePath(Position from, Position to) {
        Direction direction = from.findDirection(to, true);
        return directions.contains(direction);
    }
}
