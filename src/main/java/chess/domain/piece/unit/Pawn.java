package chess.domain.piece.unit;

import chess.domain.piece.property.Direction;
import chess.domain.piece.property.PieceFeature;
import chess.domain.piece.property.PieceInfo;
import chess.domain.piece.property.Team;
import chess.domain.position.Position;
import java.util.List;

public final class Pawn extends SpecificMovablePiece {

    private static final int START_WHITE_LINE = 2;
    private static final int START_BLACK_LINE = 7;
    private static final int FIRST_DISTANCE = 2;

    private final List<Direction> directions;

    public Pawn(final Team team) {
        super(new PieceInfo(team, PieceFeature.PAWN));
        if (team == Team.BLACK) {
            directions = List.of(Direction.SOUTH, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.SOUTH_SOUTH);
            return;
        }
        directions = List.of(Direction.NORTH, Direction.NORTHEAST, Direction.NORTHWEST, Direction.NORTH_NORTH);
    }

    @Override
    public boolean availableMove(Position source, Position target, boolean targetIsNull) {
        if (!checkPawnFirstStart(source, target)) {
            return false;
        }
        if (checkOneAndTwoSouthNorthDirections(target)) {
            validateBoardPositionIsNull(targetIsNull);
            return true;
        }
        validatePawnAttack(targetIsNull);
        return true;
    }

    @Override
    public boolean availableMove(final Position source, final Position target) {
        directionalPositions = calculateAvailableDirectionalPositions(source);
        if (availableFirstStartPosition(source, target)) {
            return true;
        }
        return containsPosition(target) && !checkFirstStart(target) && !checkFirstDistance(source, target);
    }

    public boolean checkPawnFirstStart(final Position source, final Position target) {
        directionalPositions = calculateAvailableDirectionalPositions(source);
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

    private void validatePawnAttack(final boolean isNullTarget) {
        if (isNullTarget != false) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치에 상대 기물이 없습니다.");
        }
    }

    @Override
    public boolean checkOneAndTwoSouthNorthDirections(Position target) {
        List<Direction> directions = List.of(
                Direction.SOUTH, Direction.NORTH, Direction.SOUTH_SOUTH, Direction.NORTH_NORTH);
        return directions.stream()
                .anyMatch(direction -> direction == getDirection(target));
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public List<Direction> getDirections() {
        return directions;
    }
}
