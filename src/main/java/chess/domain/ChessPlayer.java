package chess.domain;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspoint.ChessPoint;
import chess.domain.chesspoint.RelativeChessPoint;

import java.util.Map;

public class ChessPlayer {
    public static final String ERROR_VIOLATED_BY_RULE_MESSAGE = "현재의 말이 도달할 수 없는 위치입니다.";
    public static final String ERROR_INVALID_EXISTENCE_MESSAGE = "현재 위치에 말이 존재합니다.";
    public static final String ERROR_INVALID_EMPTY_MESSAGE = "현재 위치에 말이 존재하지 않습니다.";
    public static final String ERROR_CANNOT_DELETE_MESSAGE = "존재하지 않는 말을 삭제할 수 없습니다.";
    private Map<ChessPoint, ChessPiece> chessPieces;

    private ChessPlayer(Map<ChessPoint, ChessPiece> chessPieces) {
        this.chessPieces = chessPieces;
    }

    public static ChessPlayer from(Map<ChessPoint, ChessPiece> chessPieces) {
        return new ChessPlayer(chessPieces);
    }

    public boolean contains(ChessPoint source) {
        return chessPieces.containsKey(source);
    }

    public void move(ChessPoint source, ChessPoint target, ChessPlayer opponentPlayer) {
        if (isViolatedByRule(source, target)) {
            throw new InvalidChessPositionException(ERROR_VIOLATED_BY_RULE_MESSAGE);
        }

        checkMiddlePoints(source, target, opponentPlayer);

        moveChessPiece(source, target);
    }

    private void checkMiddlePoints(ChessPoint source, ChessPoint target, ChessPlayer opponentPlayer) {
        RelativeChessPoint directionOfSource = RelativeChessPoint.calculateUnitDirection(source, target);

        ChessPoint currentPoint = source.moveBy(directionOfSource);
        while (!currentPoint.equals(target)) {
            this.assertEmptyOn(currentPoint);
            opponentPlayer.assertEmptyOn(currentPoint);

            currentPoint = currentPoint.moveBy(directionOfSource);
        }
    }

    public void assertEmptyOn(ChessPoint currentPoint) {
        if (contains(currentPoint)) {
            throw new InvalidChessPositionException(ERROR_INVALID_EXISTENCE_MESSAGE);
        }
    }

    public void assertExistenceOn(ChessPoint currentPoint) {
        if (!contains(currentPoint)) {
            throw new InvalidChessPositionException(ERROR_INVALID_EMPTY_MESSAGE);
        }
    }

    private boolean isViolatedByRule(ChessPoint source, ChessPoint target) {
        ChessPiece sourceChessPiece = chessPieces.get(source);
        return !sourceChessPiece.checkRule(source, target);
    }

    private void moveChessPiece(ChessPoint source, ChessPoint target) {
        chessPieces.remove(source);
        chessPieces.put(target, chessPieces.get(source));
    }

    public void delete(ChessPoint target) {
        ChessPiece removedChessPiece = chessPieces.remove(target);
        if (removedChessPiece == null) {
            throw new InvalidChessPositionException(ERROR_CANNOT_DELETE_MESSAGE);
        }
    }
}
