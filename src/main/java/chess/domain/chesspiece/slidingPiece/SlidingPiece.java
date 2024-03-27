package chess.domain.chesspiece.slidingPiece;

import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.Team;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class SlidingPiece extends Piece {

    public SlidingPiece(Team team) {
        super(team);
    }

    @Override
    public List<Position> getMovingRoute(Position source, Position target) {
        List<Position> route = new ArrayList<>();
        validateMovingRule(source, target);

        Direction direction = Direction.findDirection(source, target);
        Position movingPosition = source.move(direction);

        while (!movingPosition.equals(target)) {
            route.add(movingPosition);
            movingPosition = movingPosition.move(direction);
        }
        return Collections.unmodifiableList(route);
    }

    @Override
    public List<Position> getAttackRoute(Position source, Position target) {
        return getMovingRoute(source, target);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
