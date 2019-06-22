package chess.domain.piece;

import chess.domain.board.Position;
import chess.exception.NotFoundPositionException;

import java.util.*;

public class ContinuousMovementPiece extends Piece {
    private final List<DirectionType> directions;

    ContinuousMovementPiece(TeamType teamType, PieceType pieceType, final List<DirectionType> directions) {
        super(teamType, pieceType);
        this.directions = directions;
    }

    @Override
    public Set<Position> makePossiblePositions(Position source, PositionChecker positionChecker) {
        Set<Position> positions = new HashSet<>();

        for (DirectionType direction : directions) {
            Position nextPosition = source.hopNextPosition(direction);
            positions.addAll(makePossiblePositionsByDirection(positionChecker, direction, nextPosition));
        }
        return positions;
    }

    // TODO 메소드 길이 리펙토링 필요
    private Set<Position> makePossiblePositionsByDirection(
            PositionChecker positionChecker, DirectionType direction, Position nextPosition) {
        Set<Position> positions = new HashSet<>();

        try {
            while (Objects.isNull(positionChecker.getPiece(nextPosition))) {
                positions.add(nextPosition);
                nextPosition = nextPosition.hopNextPosition(direction);
            }
        } catch (NotFoundPositionException e) {
            return positions;
        }

        if (!positionChecker.getPiece(nextPosition).isSameTeam(this)) {
            positions.add(nextPosition);
        }

        return positions;
    }
}
