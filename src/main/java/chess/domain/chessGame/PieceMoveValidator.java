package chess.domain.chessGame;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class PieceMoveValidator {

    public static final int NO_OBSTACLES_COUNT = 0;

    private final Map<Position, Piece> chessBoard;

    public PieceMoveValidator(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void checkPieceExistInStartPosition(Position startPosition) {
        if (!chessBoard.containsKey(startPosition)) {
            throw new IllegalArgumentException("[ERROR] 출발 좌표 위치에 말이 존재하지 않습니다.");
        }
    }

    public void checkPieceCanMove(Position startPosition, Position endPosition) {
        Piece startPiece = chessBoard.get(startPosition);
        List<Position> routeFromStartToEnd = Position.getRouteOf(startPosition, endPosition);

        checkIsMovableRoute(startPiece, routeFromStartToEnd);
        checkNoObstacleInRoute(routeFromStartToEnd);
        checkAttackOtherTeam(startPosition, endPosition);
    }

    private void checkIsMovableRoute(Piece startPiece, List<Position> routeFromStartToEnd) {
        if (!startPiece.isMovableRoute(routeFromStartToEnd)) {
            throw new IllegalArgumentException("[ERROR] 선택한 말은 목표 좌표로 이동이 불가능합니다.");
        }
    }

    private void checkNoObstacleInRoute(List<Position> routeFromStartToEnd) {
        List<Position> betweenRoute = routeFromStartToEnd.subList(1, routeFromStartToEnd.size() - 1);
        long obstacleCount = betweenRoute.stream()
                .filter(chessBoard::containsKey)
                .count();
        if (obstacleCount > NO_OBSTACLES_COUNT) {
            throw new IllegalArgumentException("[ERROR] 진행 경로 상에 다른 말이 존재합니다.");
        }
    }

    private void checkAttackOtherTeam(Position startPosition, Position endPosition) {
        Piece startPiece = chessBoard.get(startPosition);
        Piece endPiece = chessBoard.get(endPosition);
        if (endPiece == null) {
            return;
        }
        if (startPiece.isBlack() == endPiece.isBlack()) {
            throw new IllegalArgumentException("[ERROR] 목표 좌표에 같은 색 말이 있으면 이동이 불가능합니다.");
        }
    }
}
