package chess.domain.chessPiece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.domain.chessPiece.Role.*;

public class Pawn extends Piece {

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public List<Position> getRoute(Position source, Position target) {
        List<Position> route = new ArrayList<>();
        validateMovingRule(source, target);

        Direction direction = Direction.findDirection(source, target);
        Position movingPosition = direction.move(source);

        while (!movingPosition.equals(target)) {
            route.add(movingPosition);
            movingPosition = direction.move(movingPosition);
        }
        return Collections.unmodifiableList(route);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        if (!isMovable(source, target)) {
            throw new IllegalArgumentException("x");
        }
    }

    private boolean isMovable(Position source, Position target) {
        return moveForwardTwice(source, target)
                || moveForward(source, target)
                || attack(source, target);
    }

    private boolean moveForwardTwice(Position source, Position target) {
        int columnDistance = source.calculatePawnColumnDistance(target, team);
        return source.isPawnStartPosition(team) && source.isSameRow(target) && columnDistance == 2;
    }

    private boolean moveForward(Position source, Position target) {
        int columnDistance = source.calculatePawnColumnDistance(target, team);
        return source.isSameRow(target) && columnDistance == 1;
    }

    private boolean attack(Position source, Position target) {
        int rowDistance = source.calculateRowDistance(target);
        int colDistance = source.calculatePawnColumnDistance(target, team);
        return rowDistance == 1 && colDistance == 1;
    }

    @Override
    public Role getRole() {
        if (team.isWhite()) {
            return WHITE_PAWN;
        }
        return BLACK_PAWN;
    }
}
