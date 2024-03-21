package domain;

import static domain.PieceMoveResult.FAILURE;

import java.util.List;
import java.util.Optional;

abstract class AbstractMoveStraightMovePiece extends AbstractCatchOnMovePiece {
    AbstractMoveStraightMovePiece(Position position, Team team) {
        super(position, team);
    }

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
                .anyMatch(Optional::isPresent);
    }

    protected abstract Optional<PieceMoveResult> tryMoveAssumeAlone(Position targetPosition,
                                                                    PiecesOnChessBoard piecesOnChessBoard);
}
