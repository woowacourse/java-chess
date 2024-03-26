package domain.piece;

import static domain.PieceMoveResult.FAILURE;

import domain.PieceMoveResult;
import domain.PiecesOnChessBoard;
import domain.Position;
import domain.Team;
import java.util.List;
import java.util.Optional;

abstract class AbstractStraightMovePiece extends AbstractCatchOnMovePiece {
    AbstractStraightMovePiece(Position position, Team team) {
        super(position, team);
    }

    protected abstract Optional<PieceMoveResult> tryMoveAssumeAlone(Position targetPosition,
                                                                    PiecesOnChessBoard piecesOnChessBoard);

    @Override
    protected Optional<PieceMoveResult> tryMoveAssumeAloneAndCheckRoute(Position targetPosition,
                                                                        PiecesOnChessBoard piecesOnChessBoard) {
        Optional<PieceMoveResult> pieceMoveResult = tryMoveAssumeAlone(targetPosition, piecesOnChessBoard);
        if (pieceMoveResult.isPresent()) {
            return pieceMoveResult;
        }
        Position nowPosition = getPosition();
        List<Position> route = nowPosition.route(targetPosition);
        if (isAnyPieceOnRouteIsPresent(piecesOnChessBoard, route)) {
            return Optional.of(FAILURE);
        }
        return Optional.empty();
    }

    private boolean isAnyPieceOnRouteIsPresent(PiecesOnChessBoard piecesOnChessBoard, List<Position> route) {
        return route.stream()
                .map(piecesOnChessBoard::whichTeam)
                .anyMatch(Team::isPresent);
    }
}
