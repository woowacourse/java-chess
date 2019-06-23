package chess.domain.piece.piecefigure;

import chess.domain.board.Position;
import chess.domain.board.PositionChecker;
import chess.domain.piece.pieceinfo.DirectionType;
import chess.domain.piece.pieceinfo.PieceType;
import chess.domain.piece.pieceinfo.TeamType;
import chess.exception.NotFoundPositionException;

import java.util.*;

public class Pawn extends Piece {
    private static final int FIRST_MOVEMENT_COUNT = 2;
    private static final int NOT_FIRST_MOVEMENT_COUNT = 1;

    private final List<Position> defaultPositions;

    Pawn(final TeamType teamType, final PieceType pieceType, final List<Position> defaultPositions) {
        super(teamType, pieceType);
        this.defaultPositions = defaultPositions;
    }

    @Override
    public Set<Position> makePossiblePositions(Position source, PositionChecker positionChecker) {
        Set<Position> positions = new HashSet<>();
        List<DirectionType> directions = (isSameTeam(WhitePawn.of()))
                ? DirectionType.attackWhitePawnDirections()
                : DirectionType.attackBlackPawnDirections();

        for (DirectionType direction : directions) {
            positions.addAll(attackablePositionByDirection(positionChecker, direction, source));
        }
        positions.addAll(movablePositionsByDirection(positionChecker, source));
        return positions;
    }

    private Set<Position> attackablePositionByDirection(
            PositionChecker positionChecker, DirectionType direction, Position source) {
        Set<Position> position = new HashSet<>();

        try {
            position.addAll(validAttackablePositionByDirection(positionChecker, direction, source));
        } catch (NotFoundPositionException e) {
            return position;
        }
        return position;
    }

    private Set<Position> validAttackablePositionByDirection(
            PositionChecker positionChecker, DirectionType direction, Position source) {

        Position nextPosition = source.hopNextPosition(direction);

        if (isAttackablePosition(positionChecker, nextPosition)) {
            return new HashSet<>(Collections.singletonList(nextPosition));
        }
        return new HashSet<>();
    }

    private boolean isAttackablePosition(PositionChecker positionChecker, Position nextPosition) {
        return !Objects.isNull(positionChecker.getPiece(nextPosition))
                && !positionChecker.getPiece(nextPosition).isSameTeam(this);
    }

    private Set<Position> movablePositionsByDirection(PositionChecker positionChecker, Position source) {
        Set<Position> position = new HashSet<>();
        DirectionType direction = (isSameTeam(WhitePawn.of())) ? DirectionType.UP : DirectionType.DOWN;
        int movementCount = (isFirstMove(source)) ? FIRST_MOVEMENT_COUNT : NOT_FIRST_MOVEMENT_COUNT;

        try {
            Position nextPosition = source.hopNextPosition(direction);
            for (int i = 0; i < movementCount && isMovablePosition(positionChecker, nextPosition); i++) {
                position.add(nextPosition);
                nextPosition = nextPosition.hopNextPosition(direction);
            }
            return position;
        } catch (NotFoundPositionException e) {
            return position;
        }
    }

    private boolean isMovablePosition(PositionChecker positionChecker, Position nextPosition) {
        return Objects.isNull(positionChecker.getPiece(nextPosition));
    }

    private boolean isFirstMove(Position source) {
        return defaultPositions.contains(source);
    }
}
