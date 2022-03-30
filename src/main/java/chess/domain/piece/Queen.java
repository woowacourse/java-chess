package chess.domain.piece;

import chess.domain.board.Score;
import chess.domain.position.Position;
import java.util.List;
import java.util.stream.Collectors;

public class Queen extends Piece {

    private static final String name = "Q";

    private static final List<Direction> directions = Direction.pullAllBasicDirections();

    public Queen(Team team) {
        super(name, Score.QUEEN, team);
    }

    @Override
    public void movable(Position from, Position to) {
        Direction direction = from.findDirection(to, this);
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("Queen 이 움직일 수 있는 방향이 아닙니다.");
        }
    }

    @Override
    public Direction findDirection(Position from, Position to) {
        return from.findDirection(to, this);
    }

    @Override
    public boolean isStep() {
        return false;
    }

    @Override
    public List<Position> findMovablePosition(Position now) {
        return directions.stream()
                .filter(now::isMovable)
                .map(now::move)
                .collect(Collectors.toUnmodifiableList());
    }
}