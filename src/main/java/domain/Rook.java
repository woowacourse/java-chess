package domain;

import static domain.PieceMoveResult.FAILURE;
import static domain.PieceMoveResult.SUCCESS;

import java.util.List;
import java.util.Optional;

public class Rook extends AbstractPiece {
    public Rook(Position position, Team team) {
        super(position, team);
    }

    @Override
    public PieceMoveResult tryMove(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
        Position nowPosition = getPosition();
        if (!nowPosition.isSameColumn(targetPosition) && !nowPosition.isSameRow(targetPosition)) {
            return FAILURE;
        }
        List<Position> route = nowPosition.route(targetPosition);
        if (isAnyPieceOnRouteIsPresent(piecesOnChessBoard, route)) {
            return FAILURE;
        }
        if (isMyTeam(piecesOnChessBoard, targetPosition)) {
            return FAILURE;
        }
        return SUCCESS;
    }

    private boolean isMyTeam(PiecesOnChessBoard piecesOnChessBoard, Position targetPosition) {
        Optional<Team> targetTeam = piecesOnChessBoard.whichTeam(targetPosition);
        return targetTeam.isPresent() && targetTeam.get().equals(getTeam());
    }

    private boolean isAnyPieceOnRouteIsPresent(PiecesOnChessBoard piecesOnChessBoard, List<Position> route) {
        return route.stream()
                .map(piecesOnChessBoard::whichTeam)
                .anyMatch(Optional::isPresent);
    }
}
