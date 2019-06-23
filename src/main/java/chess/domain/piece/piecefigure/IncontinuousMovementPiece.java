package chess.domain.piece.piecefigure;

import chess.domain.board.Position;
import chess.domain.board.PositionChecker;
import chess.domain.piece.pieceinfo.DirectionType;
import chess.domain.piece.pieceinfo.PieceType;
import chess.domain.piece.pieceinfo.TeamType;
import chess.exception.NotFoundPositionException;

import java.util.*;

public class IncontinuousMovementPiece extends Piece {
    private final List<DirectionType> directions;

    IncontinuousMovementPiece(TeamType teamType, PieceType pieceType, final List<DirectionType> directions) {
        super(teamType, pieceType);
        this.directions = directions;
    }

    @Override
    public Set<Position> makePossiblePositions(Position source, PositionChecker positionChecker) {
        Set<Position> positions = new HashSet<>();

        for (DirectionType direction : directions) {
            positions.addAll(possiblePositionByDirection(positionChecker, direction, source));
        }
        return positions;
    }

    private Set<Position> possiblePositionByDirection(
            PositionChecker positionChecker, DirectionType direction, Position source) {

        Set<Position> position = new HashSet<>();

        try {
            position.addAll(validPositionByDirection(positionChecker, direction, source));
        } catch (NotFoundPositionException e) {
            return position;
        }
        return position;
    }

    private Set<Position> validPositionByDirection(
            PositionChecker positionChecker, DirectionType direction, Position source) {

        Position nextPosition = source.hopNextPosition(direction);

        if (isMovablePosition(positionChecker, nextPosition)) {
            return new HashSet<>(Collections.singletonList(nextPosition));
        }
        return new HashSet<>();
    }

    private boolean isMovablePosition(PositionChecker positionChecker, Position nextPosition) {
        return (Objects.isNull(positionChecker.getPiece(nextPosition))
                || !positionChecker.getPiece(nextPosition).isSameTeam(this));
    }
}
