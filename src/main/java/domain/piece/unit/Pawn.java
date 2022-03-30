package domain.piece.unit;

import domain.piece.property.PieceInfo;
import domain.piece.property.PieceFeature;
import domain.piece.property.Team;
import domain.position.Position;
import domain.piece.property.Direction;
import java.util.ArrayList;
import java.util.List;

public final class Pawn extends SpecificMovablePiece {

    private static final int START_WHITE_LINE = 2;
    private static final int START_BLACK_LINE = 7;
    private static final int FIRST_DISTANCE = 2;

    private final List<Direction> directions;

    public Pawn(final Team team) {
        super(new PieceInfo(team, PieceFeature.PAWN));
        if (team == Team.BLACK) {
            directions = Direction.blackPawnDirections();
            return;
        }
        directions = Direction.whitePawnDirections();
    }

    @Override
    public boolean availableMove(final Position source, final Position target) {
        this.directionalPositions = calculateAvailableDirectionalPositions(source);
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

    @Override
    public boolean checkOneAndTwoSouthNorthDirections(Position target){
        return Direction.oneAndTwoSouthNorthDirections().stream()
                .anyMatch(direction -> direction == getDirection(target));
    }

    @Override
    public List<Direction> getDirections() {
        return directions;
    }

    @Override
    public boolean isPawn(){
        return true;
    }
}
