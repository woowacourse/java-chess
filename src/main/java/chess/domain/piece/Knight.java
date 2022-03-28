package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {

    private static final List<Direction> directions = Direction.pullKnightDirections();

    public Knight(Team team) {
        super(PieceInfo.KNIGHT, team);
    }

    @Override
    public void movable(Position from, Position to) {
        Direction direction = from.findDirection(to);
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("Knight 이 움직일 수 있는 방향이 아닙니다.");
        }
    }

    @Override
    public Direction findDirection(Position from, Position to) {
        return from.findDirection(to);
    }

    @Override
    public List<Position> findMovablePosition(Position now) {
        return directions.stream()
                .filter(now::movable)
                .map(now::move)
                .collect(Collectors.toUnmodifiableList());
    }
}
