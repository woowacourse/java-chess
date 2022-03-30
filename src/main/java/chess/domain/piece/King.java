package chess.domain.piece;

import chess.domain.board.Score;
import chess.domain.position.Position;
import java.util.List;
import java.util.stream.Collectors;

public class King extends Piece {

    private static final String name = "K";

    private static final List<Direction> directions = Direction.pullAllBasicDirections();

    public King(Team team) {
        super(name, Score.KING, team);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public void movable(Position from, Position to) {
        Direction direction = from.findDirection(to, this);
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("King 이 움직일 수 있는 방향이 아닙니다.");
        }
    }

    @Override
    public Direction findDirection(Position from, Position to) {
        return from.findDirection(to, this);
    }

    @Override
    public List<Position> findMovablePosition(Position now) {
        return directions.stream()
                .filter(now::isMovable)
                .map(now::move)
                .collect(Collectors.toUnmodifiableList());
    }
}
