package domain.piece.unit;

import domain.piece.property.PieceSymbol;
import domain.piece.property.Team;
import domain.position.Position;
import domain.utils.Direction;
import java.util.ArrayList;
import java.util.List;

public final class Pawn extends SpecificMovablePiece {

    private static final int START_WHITE_LINE = 2;
    private static final int START_BLACK_LINE = 7;
    private static final int FIRST_DISTANCE = 2;

    private final List<Direction> directions;

    public Pawn(final Team Team) {
        super(Team, PieceSymbol.Pawn);
        if (Team == Team.BLACK) {
            directions = Direction.oneSpaceForwardDownDirections();
            return;
        }
        directions = Direction.oneSpaceForwardUpDirections();
    }

    @Override
    public boolean availableMove(final Position source, final Position target) {
        calculateAvailableDirectionalPositions(source);
        if (availableFirstStartPosition(source, target)) {
            return true;
        }
        return containsPosition(target);
    }

    private boolean availableFirstStartPosition(final Position source, final Position target) {
        return checkFirstDistance(source, target) && checkSameX(source, target) && checkTeam(source);
    }

    private boolean checkFirstDistance(final Position source, final Position target) {
        return Math.abs(source.getY() - target.getY()) == FIRST_DISTANCE;
    }

    private boolean checkSameX(final Position source, final Position target) {
        return source.getX() == target.getX();
    }

    private boolean checkTeam(final Position source) {
        boolean checkBlackTeam = checkSameTeam(Team.BLACK) && source.getY() == START_BLACK_LINE;
        boolean checkWhiteTeam = checkSameTeam(Team.WHITE) && source.getY() == START_WHITE_LINE;

        return checkBlackTeam || checkWhiteTeam;
    }

    public boolean checkUpDownDirection(final Position source, final Position target) {
        return directions().stream()
                .filter(direction -> direction == Direction.SOUTH || direction == Direction.NORTH)
                .map(direction -> directionalPositions.get(direction))
                .anyMatch(positions -> positions.contains(target)) || checkFirstDistance(source, target);
    }

    public boolean checkMoveOneSpace(final Position position) {
        return directions().stream()
                .map(direction -> directionalPositions.get(direction))
                .anyMatch(positions -> positions.contains(position));
    }

    public List<Position> calculateForwardRouteByPawn(final Position position) {
        List<Position> forwardPositions = new ArrayList<>();
        final Direction direction = directions().stream()
                .filter(direct -> direct == Direction.SOUTH || direct == Direction.NORTH)
                .findFirst()
                .orElse(null);
        forwardPositions.addAll(directionalPositions.get(direction));
        forwardPositions.add(position);

        return forwardPositions;
    }

    @Override
    protected List<Direction> directions() {
        return directions;
    }
}
