package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends Piece {

    private static final List<Direction> directions = Direction.pullDiagonalDirections();

    public Bishop(Team team) {
        super(PieceInfo.BISHOP, team);
    }

    @Override
    public void movable(Position from, Position to) {
        Direction direction = from.findDirectionByCompactValue(to);
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("Bishop이 움직일 수 있는 방향이 아닙니다.");
        }
    }

    @Override
    public Direction findDirection(Position from, Position to) {
        return from.findDirectionByCompactValue(to);
    }

    @Override
    public List<Position> findMovablePosition(Position now) {
        return directions.stream()
                .filter(now::movable)
                .map(now::move)
                .collect(Collectors.toUnmodifiableList());
    }
}
