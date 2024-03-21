package domain.chess.piece;

import static domain.chess.piece.PieceMoveResult.CATCH;
import static domain.chess.piece.PieceMoveResult.FAILURE;
import static domain.chess.piece.PieceMoveResult.SUCCESS;

import domain.Position;
import domain.chess.board.ChessBoard;
import java.util.List;
import java.util.Optional;

public final class Pawn extends AbstractPiece {

    private final Position initialPosition;

    public Pawn(Position position, Team team) {
        super(position, team);
        this.initialPosition = position;
    }

    @Override
    public PieceMoveResult tryMove(Position targetPosition, ChessBoard chessBoard) {
        if (isMoveForward(targetPosition) && isEmpty(targetPosition, chessBoard)) {
            return SUCCESS;
        }
        if (isMoveForwardTwo(targetPosition, chessBoard) && isEmpty(targetPosition, chessBoard)) {
            return SUCCESS;
        }
        if (isMoveDiagonal(targetPosition) && isOtherTeam(targetPosition, chessBoard)) {
            return CATCH;
        }
        return FAILURE;
    }

    private boolean isMoveForward(Position targetPosition) {
        Position nowPosition = getPosition();
        boolean sameColumn = nowPosition.isSameColumn(targetPosition);
        boolean rightDirection = nowPosition.rowDistance(targetPosition) == forwardDirection();
        return rightDirection && sameColumn;
    }

    private int forwardDirection() {
        Team team = getTeam();
        if (team.equals(Team.WHITE)) {
            return 1;
        }
        return -1;
    }

    private boolean isEmpty(Position targetPosition, ChessBoard chessBoard) {
        Optional<Team> targetTeam = chessBoard.whichTeam(targetPosition);
        return targetTeam.isEmpty();
    }

    private boolean isMoveForwardTwo(Position targetPosition, ChessBoard chessBoard) {
        Position nowPosition = getPosition();
        List<Position> route = nowPosition.route(targetPosition);
        boolean rightDirection = nowPosition.rowDistance(targetPosition) == 2 * forwardDirection();
        boolean sameColumn = nowPosition.isSameColumn(targetPosition);
        boolean firstMove = nowPosition.equals(initialPosition);
        boolean allPieceOnRouteIsEmpty = isAllPieceOnRouteIsEmpty(chessBoard, route);
        return rightDirection && sameColumn && firstMove && allPieceOnRouteIsEmpty;
    }

    private boolean isAllPieceOnRouteIsEmpty(ChessBoard chessBoard, List<Position> route) {
        return route.stream()
                .map(chessBoard::whichTeam)
                .allMatch(Optional::isEmpty);
    }

    private boolean isMoveDiagonal(Position targetPosition) {
        Position nowPosition = getPosition();
        int rowDistance = nowPosition.rowDistance(targetPosition);
        int columnDistance = nowPosition.columnDistance(targetPosition);
        return rowDistance == forwardDirection() && Math.abs(columnDistance) == 1;
    }

    private boolean isOtherTeam(Position targetPosition, ChessBoard chessBoard) {
        Optional<Team> targetTeam = chessBoard.whichTeam(targetPosition);
        return targetTeam.isPresent() && !targetTeam.get().equals(getTeam());
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }
}
