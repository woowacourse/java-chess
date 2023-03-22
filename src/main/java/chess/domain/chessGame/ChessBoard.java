package chess.domain.chessGame;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ChessBoard {

    private final Map<Position, Piece> chessBoard;

    public ChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = new HashMap<>(chessBoard);
    }

    public void movePiece(Position startPosition, Position endPosition) {
        validateExistPieceInStartPosition(startPosition);
        Piece startPiece = chessBoard.get(startPosition);
        if (startPiece.getPieceType() == PieceName.PAWN) {
            considerPawnCase(startPosition, endPosition);
        }
        validateCanMove(startPosition, endPosition);
        executeMove(startPosition, endPosition);
    }

    private void validateExistPieceInStartPosition(Position startPosition) {
        if (!chessBoard.containsKey(startPosition)) {
            throw new IllegalArgumentException("[ERROR] 출발 좌표 위치에 말이 존재하지 않습니다.");
        }
    }

    private void considerPawnCase(Position startPosition, Position endPosition) {
        List<Position> routeFromStartToEnd = Position.getRouteOf(startPosition, endPosition);
        Piece startPiece = chessBoard.get(startPosition);

        if (startPiece.isMovableRoute(routeFromStartToEnd)) {
            validatePawnCanMoveForward(startPosition, endPosition, routeFromStartToEnd);
            validatePawnCanMoveDiagonal(startPosition, endPosition);
        }
    }

    private void validatePawnCanMoveForward(Position startPosition, Position endPosition, List<Position> routeFromStartToEnd) {
        if (Direction.of(startPosition, endPosition) == Direction.CROSS) {
            validateNoObstacleBetweenRoute(routeFromStartToEnd);
            validateBlankInEndPosition(endPosition);
        }
    }

    private void validateBlankInEndPosition(Position endPosition) {
        if (chessBoard.containsKey(endPosition)) {
            throw new IllegalArgumentException("[ERROR] 폰은 직선 상 이동 경로에 말이 있으면 이동이 불가능합니다.");
        }
    }

    private void validatePawnCanMoveDiagonal(Position startPosition, Position endPosition) {
        if (Direction.of(startPosition, endPosition) == Direction.DIAGONAL) {
            validateExistPieceInEndPosition(endPosition);
            validateAttackDifferentTeam(startPosition, endPosition);
        }
    }

    private void validateExistPieceInEndPosition(Position endPosition) {
        if (!chessBoard.containsKey(endPosition)) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각선 이동 경로에 말이 없으면 이동이 불가능합니다.");
        }
    }

    private void validateCanMove(Position startPosition, Position endPosition) {
        Piece startPiece = chessBoard.get(startPosition);
        List<Position> routeFromStartToEnd = Position.getRouteOf(startPosition, endPosition);

        validateIsMovableRoute(startPiece, routeFromStartToEnd);
        validateNoObstacleBetweenRoute(routeFromStartToEnd);
        validateAttackDifferentTeam(startPosition, endPosition);
    }

    private void validateIsMovableRoute(Piece startPiece, List<Position> routeFromStartToEnd) {
        if (!startPiece.isMovableRoute(routeFromStartToEnd)) {
            throw new IllegalArgumentException("[ERROR] 선택한 말은 목표 좌표로 이동이 불가능합니다.");
        }
    }

    private void validateNoObstacleBetweenRoute(List<Position> routeFromStartToEnd) {
        List<Position> betweenRoute = routeFromStartToEnd.subList(1, routeFromStartToEnd.size() - 1);
        long obstacleCount = betweenRoute.stream()
                .filter(chessBoard::containsKey)
                .count();
        if (obstacleCount > 0) {
            throw new IllegalArgumentException("[ERROR] 진행 경로 상에 다른 말이 존재합니다.");
        }
    }

    private void validateAttackDifferentTeam(Position startPosition, Position endPosition) {
        Piece startPiece = chessBoard.get(startPosition);
        Piece endPiece = chessBoard.get(endPosition);
        if (endPiece == null) {
            return;
        }
        if (startPiece.isBlack() == endPiece.isBlack()) {
            throw new IllegalArgumentException("[ERROR] 목표 좌표에 같은 색 말이 있으면 이동이 불가능합니다.");
        }
    }

    private void executeMove(Position startPosition, Position endPosition) {
        chessBoard.put(endPosition, chessBoard.get(startPosition));
        chessBoard.remove(startPosition);
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }
}
