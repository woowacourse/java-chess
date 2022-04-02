package chess.domain.chessboard;

import chess.domain.Score;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.position.Position;
import chess.result.MoveResult;
import java.util.Map;
import java.util.Objects;

public class ChessBoard {

    private final Map<Position, ChessPiece> pieceByPosition;
    private Color currentTurnColor;

    public ChessBoard(final Map<Position, ChessPiece> pieceByPosition) {
        this.pieceByPosition = pieceByPosition;
        currentTurnColor = Color.WHITE;
    }

    public ChessPiece findPiece(final Position position) {
        return pieceByPosition.get(position);
    }

    public MoveResult move(final Position from, final Position to) {
        final ChessPiece movablePiece = findPiece(from);
        if (Objects.isNull(movablePiece)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }
        if (!movablePiece.isSameColor(currentTurnColor)) {
            throw new IllegalArgumentException(currentTurnColor.name() + "의 차례입니다.");
        }

        checkCanMove(from, to, movablePiece);
        movePiece(from, to);
        return new MoveResult(isKingDie(), pieceByPosition);
    }

    private void checkCanMove(final Position from, final Position to, final ChessPiece movablePiece) {
        movablePiece.checkMovablePosition(from, to, findPiece(to));
        checkHurdle(from, to, movablePiece);
    }

    private void checkHurdle(final Position from, final Position to, final ChessPiece movablePiece) {
        final boolean hurdleExist = movablePiece.findRoute(from, to).stream()
                .map(this::findPiece)
                .anyMatch(Objects::nonNull);

        if (hurdleExist) {
            throw new IllegalArgumentException("이동 경로 사이에 다른 기물이 있습니다.");
        }
    }

    private void movePiece(final Position from, final Position to) {
        final ChessPiece movablePiece = pieceByPosition.remove(from);
        pieceByPosition.put(to, movablePiece);
        currentTurnColor = currentTurnColor.toOpposite();
    }

    public boolean isKingDie() {
        final long kingCount = pieceByPosition.values()
                .stream()
                .filter(ChessPiece::isKing)
                .count();
        return kingCount != 2;
    }

    public Map<Position, ChessPiece> findAllPiece() {
        return pieceByPosition;
    }

    public Score calculateScore() {
        return new Score(pieceByPosition);
    }

    public Color currentTurn() {
        return currentTurnColor;
    }

    public Color findWinColor() {
        if (isKingDie()) {
            final ChessPiece aliveKing = findAliveKing();
            return aliveKing.color();
        }
        final Score score = calculateScore();
        if (score.findScore(Color.BLACK) > score.findScore(Color.WHITE)) {
            return Color.BLACK;
        }
        if (score.findScore(Color.WHITE) > score.findScore(Color.BLACK)) {
            return Color.WHITE;
        }
        return null;
    }

    private ChessPiece findAliveKing() {
        return pieceByPosition.values()
                .stream()
                .filter(ChessPiece::isKing)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("킹이 존재하지 않습니다."));
    }
}
