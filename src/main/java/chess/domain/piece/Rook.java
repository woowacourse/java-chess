package chess.domain.piece;

import chess.domain.board.Score;
import chess.domain.position.Position;
import java.util.List;
import java.util.stream.Collectors;

public class Rook extends Piece {

    private static final String name = "R";

    private static final List<Direction> directions = Direction.pullStraightDirections();

    public Rook(Team team) {
        super(name, team);
    }

    @Override
    public void movable(Position from, Position to) {
        Direction direction = from.findDirection(to, this);
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("Rook 이 움직일 수 있는 방향이 아닙니다.");
        }
    }

    @Override
    public boolean isStep() {
        return false;
    }

    @Override
    public List<Position> findMovablePosition(Position now) {
        return this.directions.stream()
                .filter(now::isMovable)
                .map(now::move)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public float getScore() {
        return Score.ROOK.getValue();
    }
}
