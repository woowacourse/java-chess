package chess.domain.chessround;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.King;
import chess.domain.chesspoint.ChessPoint;
import chess.domain.chesspoint.RelativeChessPoint;
import chess.domain.util.Counter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ChessPlayer {
    private static final String ERROR_VIOLATED_BY_RULE_MESSAGE = "현재의 말이 도달할 수 없는 위치입니다.";
    private static final String ERROR_INVALID_EXISTENCE_MESSAGE = "현재 위치에 말이 존재합니다.";
    private static final String ERROR_INVALID_EMPTY_MESSAGE = "현재 위치에 말이 존재하지 않습니다.";
    private static final String ERROR_CANNOT_DELETE_MESSAGE = "존재하지 않는 말을 삭제할 수 없습니다.";
    private Map<ChessPoint, ChessPiece> alivePieces;

    private ChessPlayer(Map<ChessPoint, ChessPiece> alivePieces) {
        this.alivePieces = alivePieces;
    }

    public static ChessPlayer from(Map<ChessPoint, ChessPiece> chessPieces) {
        return new ChessPlayer(chessPieces);
    }

    public boolean contains(ChessPoint source) {
        return alivePieces.containsKey(source);
    }

    public void move(ChessPoint source, ChessPoint target, ChessPlayer opponentPlayer) {
        if (isViolatedByRule(source, target, opponentPlayer.contains(target))) {
            throw new InvalidChessPositionException(ERROR_VIOLATED_BY_RULE_MESSAGE);
        }

        checkMiddlePoints(source, target, opponentPlayer);

        moveChessPiece(source, target);
    }

    private boolean isViolatedByRule(ChessPoint source, ChessPoint target, boolean opponentPieceOnTarget) {
        ChessPiece sourceChessPiece = alivePieces.get(source);
        return !sourceChessPiece.checkRule(source, target, opponentPieceOnTarget);
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

    private void moveChessPiece(ChessPoint source, ChessPoint target) {
        ChessPiece chessPiece = alivePieces.get(source);
        alivePieces.put(target, chessPiece);
        alivePieces.remove(source);
    }

    public void delete(ChessPoint target) {
        ChessPiece removedChessPiece = alivePieces.remove(target);
        if (removedChessPiece == null) {
            throw new InvalidChessPositionException(ERROR_CANNOT_DELETE_MESSAGE);
        }
    }

    public double calculateScore() {
        Counter<Integer> pawnCounter = Counter.create();
        pawnCounter = initializeCounter(pawnCounter);

        return calculateScoreBy(pawnCounter);
    }

    private Counter<Integer> initializeCounter(Counter<Integer> pawnCounter) {
        for (Entry<ChessPoint, ChessPiece> entry : alivePieces.entrySet()) {
            ChessPoint chessPoint = entry.getKey();
            ChessPiece chessPiece = entry.getValue();

            int column = chessPoint.getColumn();
            pawnCounter = chessPiece.countPiecesOnSameColumn(pawnCounter, column);
        }
        return pawnCounter;
    }

    private double calculateScoreBy(Counter<Integer> pawnCounter) {
        double sum = 0;
        for (Entry<ChessPoint, ChessPiece> entry : alivePieces.entrySet()) {
            ChessPoint chessPoint = entry.getKey();
            ChessPiece chessPiece = entry.getValue();

            int column = chessPoint.getColumn();
            sum += chessPiece.getScore(pawnCounter, column);
        }
        return sum;
    }

    public Set<ChessPoint> getAllChessPoints() {
        return alivePieces.keySet();
    }

    public ChessPiece get(ChessPoint point) {
        return alivePieces.get(point);
    }

    public boolean isKingAlive() {
        return alivePieces.values()
                .stream()
                .anyMatch(chessPiece -> (chessPiece == King.getInstance()))
                ;
    }
}
