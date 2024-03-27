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
    public List<Position> findRoute(Position source, Position target, Piece targetPiece) {
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
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
