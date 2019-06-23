package chess.domain.piece.piecefigure;

import chess.domain.board.Position;
import chess.domain.board.PositionChecker;
import chess.domain.piece.pieceinfo.DirectionType;
import chess.domain.piece.pieceinfo.PieceType;
import chess.domain.piece.pieceinfo.TeamType;
import chess.exception.NotFoundPositionException;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
            positions.addAll(makeAttackablePositionsByDirection(positionChecker, direction, source));
        }
        positions.addAll(makeMovablePositionsByDirection(positionChecker, source));
        return positions;
    }

    private Set<Position> makeAttackablePositionsByDirection(
            PositionChecker positionChecker, DirectionType direction, Position source) {
        Set<Position> position = new HashSet<>();

        try {
            Position nextPosition = source.hopNextPosition(direction);
            if (!Objects.isNull(positionChecker.getPiece(nextPosition))
                    && !positionChecker.getPiece(nextPosition).isSameTeam(this)) {
                position.add(nextPosition);
            }
        } catch (NotFoundPositionException e) {
            return position;
        }
        return position;
    }

    // TODO 리팩토링 필요함
    private Set<Position> makeMovablePositionsByDirection(PositionChecker positionChecker, Position source) {
        Set<Position> position = new HashSet<>();
        DirectionType direction = (isSameTeam(WhitePawn.of())) ? DirectionType.UP : DirectionType.DOWN;
        int movementCount = (isFirstMove(source)) ? FIRST_MOVEMENT_COUNT : NOT_FIRST_MOVEMENT_COUNT;

        try {
            Position nextPosition = source.hopNextPosition(direction);
            for (int i = 0; i < movementCount && Objects.isNull(positionChecker.getPiece(nextPosition)); i++) {
                position.add(nextPosition);
                nextPosition = nextPosition.hopNextPosition(direction);
            }
        } catch (NotFoundPositionException e) {
            return position;
        }
        return position;
    }

    private boolean isFirstMove(Position source) {
        return defaultPositions.contains(source);
    }
}
