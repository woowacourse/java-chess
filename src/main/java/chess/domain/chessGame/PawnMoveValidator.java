package chess.domain.chessGame;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class PawnMoveValidator {

    private final Map<Position, Piece> chessBoard;

    public PawnMoveValidator(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void checkPawnCanMove(Position startPosition, Position endPosition) {
        List<Position> routeFromStartToEnd = Position.getRouteOf(startPosition, endPosition);
        Piece startPiece = chessBoard.get(startPosition);

        if (startPiece.isMovableRoute(routeFromStartToEnd)) {
            checkPawnCanMoveForward(startPosition, endPosition);
            checkPawnCanMoveDiagonal(startPosition, endPosition);
        }
    }

    private void checkPawnCanMoveForward(Position startPosition, Position endPosition) {
        if (Direction.of(startPosition, endPosition) == Direction.CROSS) {
            checkBlankInEndPosition(endPosition);
        }
    }

    private void checkBlankInEndPosition(Position endPosition) {
        if (chessBoard.containsKey(endPosition)) {
            throw new IllegalArgumentException("[ERROR] 폰은 전진할 때 말이 없는 곳으로만 전진할 수 있습니다.");
        }
    }

    private void checkPawnCanMoveDiagonal(Position startPosition, Position endPosition) {
        if (Direction.of(startPosition, endPosition) == Direction.DIAGONAL) {
            checkExistPieceInEndPosition(endPosition);
        }
    }

    private void checkExistPieceInEndPosition(Position endPosition) {
        if (!chessBoard.containsKey(endPosition)) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각선 이동 경로에 말이 없으면 이동이 불가능합니다.");
        }
    }
}
