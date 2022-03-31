package domain.piece.unit;

import static domain.piece.property.Direction.*;

import domain.piece.property.PieceInfo;
import domain.piece.property.PieceFeature;
import domain.piece.property.Team;
import domain.position.Position;
import domain.piece.property.Direction;
import java.util.List;

public final class Pawn extends SpecificMovablePiece {

    private static final int START_WHITE_LINE = 2;
    private static final int START_BLACK_LINE = 7;
    private static final int FIRST_DISTANCE = 2;

    private final List<Direction> directions;

    public Pawn(final Team team) {
        super(new PieceInfo(team, PieceFeature.PAWN));
        if (team == Team.BLACK) {
            directions = List.of(SOUTH, SOUTHEAST, SOUTHWEST, SOUTH_SOUTH);
            return;
        }
        directions = List.of(NORTH, NORTHEAST, NORTHWEST, NORTH_NORTH);
    }

    @Override
    public boolean availableMove(Position source, Position target, boolean isNullRoute, boolean isNullTarget) {
        if (!availableMove(source, target)) {
            return false;
        }
        if (checkOneAndTwoSouthNorthDirections(target)) {
            validateBoardPositionIsNull(isNullTarget);
            validateRouteIsNull(isNullRoute);
            return true;
        }
        validatePawnAttack(isNullTarget);
        return true;
    }

    @Override
    public boolean availableMove(final Position source, final Position target) {
        calculateDirections(source);
        if (availableFirstStartPosition(source, target)) {
            return true;
        }
        return containsPosition(target) && !checkFirstStart(target) && !checkFirstDistance(source, target);
    }

    private boolean availableFirstStartPosition(final Position source, final Position target) {
        return checkFirstDistance(source, target) && checkSameX(source, target) && checkFirstStart(source);
    }

    private boolean checkFirstDistance(final Position source, final Position target) {
        return Math.abs(source.getYPosition() - target.getYPosition()) == FIRST_DISTANCE;
    }

    private boolean checkSameX(final Position source, final Position target) {
        return source.getXPosition() == target.getXPosition();
    }

    private boolean checkFirstStart(final Position source) {
        boolean checkBlackTeam = checkSameTeam(Team.BLACK) && source.getYPosition() == START_BLACK_LINE;
        boolean checkWhiteTeam = checkSameTeam(Team.WHITE) && source.getYPosition() == START_WHITE_LINE;

        return checkBlackTeam || checkWhiteTeam;
    }

    private void validateBoardPositionIsNull(final boolean isNullTarget) {
        if (isNullTarget != true) {
            throw new IllegalArgumentException("[ERROR] Target에 다른 기물이 존재합니다.");
        }
    }

    private void validateRouteIsNull(boolean isNullRoute) {
        if (isNullRoute != true) {
            throw new IllegalArgumentException("[ERROR] 경로에 다른 기물이 존재합니다.");
        }
    }

    private void validatePawnAttack(final boolean isNullTarget) {
        if (isNullTarget != false) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치에 상대 기물이 없습니다.");
        }
    }

    @Override
    public boolean checkOneAndTwoSouthNorthDirections(Position target) {
        List<Direction> directions = List.of(SOUTH, NORTH, SOUTH_SOUTH, NORTH_NORTH);
        return directions.stream()
                .anyMatch(direction -> direction == getDirection(target));
    }

    @Override
    public List<Direction> getDirections() {
        return directions;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
