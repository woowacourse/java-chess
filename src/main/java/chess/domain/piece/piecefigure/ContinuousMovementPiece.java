package chess.domain.piece.piecefigure;

import chess.domain.board.Position;
import chess.domain.board.PositionChecker;
import chess.domain.piece.pieceinfo.DirectionType;
import chess.domain.piece.pieceinfo.PieceType;
import chess.domain.piece.pieceinfo.TeamType;
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
            positions.addAll(possiblePositionsByDirection(positionChecker, direction, source));
        }
        return positions;
    }

    private Set<Position> possiblePositionsByDirection(
            PositionChecker positionChecker, DirectionType direction, Position source) {

        Set<Position> positions = new HashSet<>();
        Position nextPosition;

        try {
            nextPosition = source.hopNextPosition(direction);
            while (positionChecker.getPiece(nextPosition).isSameTeam(Blank.of())) {
                positions.add(nextPosition);
                nextPosition = nextPosition.hopNextPosition(direction);
            }
        } catch (NotFoundPositionException e) {
            return positions;
        }
        return checkLastPosition(positionChecker, positions, nextPosition);
    }

    private Set<Position> checkLastPosition(
            PositionChecker positionChecker, Set<Position> positions, Position lastPosition) {
        if (!positionChecker.getPiece(lastPosition).isSameTeam(this)) {
            positions.add(lastPosition);
        }
        return positions;
    }
}
