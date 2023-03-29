package chess.domain.chessGame;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public final class PieceMoveValidator {

    private PieceMoveValidator() {
    }

    private static final int NO_OBSTACLES_COUNT = 0;

    public static void checkPieceExistInStartPosition(Map<Position, Piece> chessBoard, Position startPosition) {
        if (!chessBoard.containsKey(startPosition)) {
            throw new IllegalArgumentException("[ERROR] 출발 좌표 위치에 말이 존재하지 않습니다.");
        }
    }

    public static void checkTurn(Map<Position, Piece> chessBoard, Position startPosition, Color turn) {
        Piece startPiece = chessBoard.get(startPosition);
        if (startPiece.getColor() != turn) {
            throw new IllegalArgumentException("[ERROR] " + turn.name() + "의 차례입니다.");
        }
    }

    public static void checkPieceCanMove(Map<Position, Piece> chessBoard, Position startPosition, Position endPosition) {
        Piece startPiece = chessBoard.get(startPosition);
        if (startPiece.getPieceType() == PieceType.PAWN) {
            PawnMoveValidator.checkPawnCanMove(chessBoard, startPosition, endPosition);
        }

        List<Position> routeFromStartToEnd = Position.getRouteOf(startPosition, endPosition);
        checkIsMovableRoute(startPiece, routeFromStartToEnd);
        checkNoObstacleInRoute(chessBoard, routeFromStartToEnd);
        checkAttackOtherTeam(chessBoard, startPosition, endPosition);
    }

    private static void checkIsMovableRoute(Piece startPiece, List<Position> routeFromStartToEnd) {
        if (!startPiece.isMovableRoute(routeFromStartToEnd)) {
            throw new IllegalArgumentException("[ERROR] 선택한 말은 목표 좌표로 이동이 불가능합니다.");
        }
    }

    private static void checkNoObstacleInRoute(Map<Position, Piece> chessBoard, List<Position> routeFromStartToEnd) {
        List<Position> betweenRoute = routeFromStartToEnd.subList(1, routeFromStartToEnd.size() - 1);
        long obstacleCount = betweenRoute.stream()
                .filter(chessBoard::containsKey)
                .count();
        if (obstacleCount > NO_OBSTACLES_COUNT) {
            throw new IllegalArgumentException("[ERROR] 진행 경로 상에 다른 말이 존재합니다.");
        }
    }

    private static void checkAttackOtherTeam(Map<Position, Piece> chessBoard, Position startPosition, Position endPosition) {
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
