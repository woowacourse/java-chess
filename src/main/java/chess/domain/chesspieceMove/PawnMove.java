package chess.domain.chesspieceMove;

import chess.domain.Position;
import chess.domain.chesspiece.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PawnMove implements Move {
    private static final int MIN_DISTANCE = 1;
    private static final int MAX_DISTANCE = 2;

    private static PawnMove whitePawnMove;
    private static PawnMove blackPawnMove;

    private int direction;
    private int moveFlag = 2;

    private PawnMove(Team team) {
        this.direction = team.getDirection();
    }

    public static PawnMove getInstance(Team team) {
        if (Team.BLACK.getDirection() == team.getDirection() && Objects.isNull(blackPawnMove)) {
            return new PawnMove(team);
        }

        if (Team.BLACK.getDirection() == team.getDirection()) {
            return blackPawnMove;
        }

        if (Objects.isNull(whitePawnMove))
            return new PawnMove(team);
        return whitePawnMove;
    }

    @Override
    public List<Position> move(Position source, Position target) {
        if (!isInRoute(source, target)) {
            throw new IllegalArgumentException();
        }

        List<Position> positions = new ArrayList<>();
        addRoute(source, target, positions);

        if (moveFlag == 2) changeFlagStatus();

        return positions;
    }

    private void addRoute(Position source, Position target, List<Position> positions) {
        int distance = Math.abs(target.calculateRowDistance(source));

        if (distance == MAX_DISTANCE) {
            positions.add(Position.of(source.getY() + direction, source.getX()));
        }
        positions.add(target);
    }

    @Override
    public boolean isInRoute(Position source, Position target) {
        int rowDistance = target.calculateRowDistance(source);

        return !source.equals(target)
                && isInFront(source, target, rowDistance) || isInRange(rowDistance);
    }

    private boolean isInRange(int rowDistance) {
        return rowDistance * direction >= MIN_DISTANCE && rowDistance * direction <= moveFlag;
    }

    private boolean isInFront(Position source, Position target, int rowDistance) {
        return rowDistance == direction && Math.abs(source.calculateColumnDistance(target)) == MIN_DISTANCE;
    }

    private void changeFlagStatus() {
        moveFlag = 1;
    }
}
