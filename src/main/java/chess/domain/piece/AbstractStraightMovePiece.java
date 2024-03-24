package chess.domain.piece;


import chess.domain.Position;
import chess.domain.board.ChessBoard;
import java.util.List;
import java.util.Optional;

abstract class AbstractStraightMovePiece extends AbstractCatchOnMovePiece {
    AbstractStraightMovePiece(Position position, Team team) {
        super(position, team);
    }

    @Override
    protected Optional<PieceMoveResult> tryMoveAssumeAloneAndCheckRoute(Position targetPosition,
                                                                        ChessBoard chessBoard) {
        Optional<PieceMoveResult> pieceMoveResult = tryMoveAssumeAlone(targetPosition, chessBoard);
        if (pieceMoveResult.isPresent()) {
            return pieceMoveResult;
        }
        Position nowPosition = getPosition();
        List<Position> route = nowPosition.route(targetPosition);
        if (isAnyPieceOnRouteIsPresent(chessBoard, route)) {
            return Optional.of(PieceMoveResult.FAILURE);
        }
        return Optional.empty();
    }

    private boolean isAnyPieceOnRouteIsPresent(ChessBoard chessBoard, List<Position> route) {
        return route.stream()
                .map(chessBoard::whichTeam)
                .anyMatch(Optional::isPresent);
    }

    protected abstract Optional<PieceMoveResult> tryMoveAssumeAlone(Position targetPosition, ChessBoard chessBoard);
}
