package domain.piece.unit;

import domain.piece.Piece;
import domain.piece.SpecificMovablePiece;
import domain.piece.property.PieceSymbol;
import domain.piece.property.TeamColor;
import domain.position.Position;
import domain.utils.Direction;
import java.util.List;

public final class Pawn extends SpecificMovablePiece {

    private static final int START_WHITE_LINE = 2;
    private static final int START_BLACK_LINE = 7;
    public static final int FIRST_DISTANCE = 2;

    private final List<Direction> directions;

    public Pawn(final TeamColor teamColor) {
        super(teamColor, PieceSymbol.Pawn);
        if (teamColor == teamColor.BLACK) {
            directions = Direction.oneSpaceForwardDownDirections();
            return;
        }
        directions = Direction.oneSpaceForwardUpDirections();
    }

    @Override
    public boolean availableMove(Position source, Position target) {
        if (availableFirstStartPosition(source, target)) {
            return true;
        }
        calculateAvailablePositions(source);
        return containsTarget(target);
    }

    private boolean availableFirstStartPosition(Position source, Position target) {
        return checkFirstDistance(source, target) && checkSameX(source, target) && checkColor(source);
    }

    private boolean checkFirstDistance(Position source, Position target) {
        return Math.abs(source.getY() - target.getY()) == FIRST_DISTANCE;
    }

    private boolean checkSameX(Position source, Position target) {
        return source.getX() == target.getX();
    }

    private boolean checkColor(Position source) {
        boolean checkBlackColor = checkSameTeamColor(TeamColor.BLACK) && source.getY() == START_BLACK_LINE;
        boolean checkWhiteColor = checkSameTeamColor(TeamColor.WHITE) && source.getY() == START_WHITE_LINE;
        return checkBlackColor || checkWhiteColor;
    }

    @Override
    public List<Direction> directions() {
        return directions;
    }
}
