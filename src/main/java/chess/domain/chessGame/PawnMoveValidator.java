package chess.domain.chessGame;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public final class PawnMoveValidator {

    private PawnMoveValidator() {
    }

    public static void checkPawnCanMove(Map<Position, Piece> chessBoard, Position startPosition, Position endPosition) {
        List<Position> routeFromStartToEnd = Position.getRouteOf(startPosition, endPosition);
        Piece startPiece = chessBoard.get(startPosition);

        if (startPiece.isMovableRoute(routeFromStartToEnd)) {
            checkPawnCanMoveForward(chessBoard, startPosition, endPosition);
            checkPawnCanMoveDiagonal(chessBoard, startPosition, endPosition);
        }
    }

    private static void checkPawnCanMoveForward(Map<Position, Piece> chessBoard, Position startPosition, Position endPosition) {
        if (Direction.of(startPosition, endPosition) == Direction.CROSS) {
            checkBlankInEndPosition(chessBoard, endPosition);
        }
    }

    private static void checkBlankInEndPosition(Map<Position, Piece> chessBoard, Position endPosition) {
        if (chessBoard.containsKey(endPosition)) {
            throw new IllegalArgumentException("[ERROR] 폰은 전진할 때 말이 없는 곳으로만 전진할 수 있습니다.");
        }
    }

    private static void checkPawnCanMoveDiagonal(Map<Position, Piece> chessBoard, Position startPosition, Position endPosition) {
        if (Direction.of(startPosition, endPosition) == Direction.DIAGONAL) {
            checkExistPieceInEndPosition(chessBoard, endPosition);
        }
    }

    private static void checkExistPieceInEndPosition(Map<Position, Piece> chessBoard, Position endPosition) {
        if (!chessBoard.containsKey(endPosition)) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각선 이동 경로에 말이 없으면 이동이 불가능합니다.");
        }
    }
}
