package chess.domain;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspoint.ChessPoint;
import chess.domain.chesspoint.RelativeChessPoint;

import java.util.Map;

public class ChessPlayer {
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
            throw new IllegalArgumentException();
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

    private void assertEmptyOn(ChessPoint currentPoint) {
        if (contains(currentPoint)) {
            throw new IllegalArgumentException();
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
            throw new IllegalArgumentException("");
        }
    }
}
