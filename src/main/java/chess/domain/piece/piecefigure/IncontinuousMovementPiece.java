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
            if (makePossiblePositionByDirection(positionChecker, direction, source)) {
                positions.add(source.hopNextPosition(direction));
            }
        }
        return positions;
    }

    // TODO 메소드 길이 리펙토링 필요
    private boolean makePossiblePositionByDirection(PositionChecker positionChecker, DirectionType direction, Position source) {
        try {
            Position nextPosition = source.hopNextPosition(direction);
            if (Objects.isNull(positionChecker.getPiece(nextPosition)) ||
                    !positionChecker.getPiece(nextPosition).isSameTeam(this)) {
                return true;
            }
        } catch (NotFoundPositionException e) {
            e.getMessage();
        }
        return false;
    }
}
