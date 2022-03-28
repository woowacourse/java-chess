package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.CoordinateX;
import chess.domain.position.Position;
import chess.domain.position.CoordinateY;

import java.util.Collections;
import java.util.Map;
import java.util.function.BiConsumer;

public class Board {

    private static final int NEXT = 1;

    private final Map<Position, Piece> value;

    public Board(final Initializable initializable) {
        value = initializable.init();
    }

    public boolean isInitialized(final Initializable initializable) {
        return value.equals(initializable.init());
    }

    public void move(final Position from, final Position to) {
        final Piece piece = getPiece(from);
        piece.checkMovingRange(this, from, to);
        value.put(to, value.remove(from));
    }

    public void showStatus(final BiConsumer<String, Double> printScore) {
        showColorStatus(printScore, Color.WHITE);
        showColorStatus(printScore, Color.BLACK);
    }

    public boolean isMatchedColor(final Position target, final Color color) {
        Piece piece = getPiece(target);
        return piece.isSameColor(color);
    }

    public boolean hasPieceInXAxis(final Position from, final Position to) {
        int minY = Math.min(from.getCoordinateY(), to.getCoordinateY());
        int maxY = Math.max(from.getCoordinateY(), to.getCoordinateY());

        return value.keySet().stream()
                .filter(position -> position.getCoordinateX().equals(from.getCoordinateX()))
                .filter(position -> position.getCoordinateY() > minY)
                .anyMatch(position -> position.getCoordinateY() < maxY);
    }

    public boolean hasPieceInYAxis(final Position from, final Position to) {
        int minX = CoordinateX.min(from.getCoordinateX(), to.getCoordinateX());
        int maxX = CoordinateX.max(from.getCoordinateX(), to.getCoordinateX());

        return value.keySet().stream()
                .filter(position -> position.getCoordinateY() == from.getCoordinateY())
                .filter(position -> position.getCoordinateXOrder() > minX)
                .anyMatch(position -> position.getCoordinateXOrder() < maxX);
    }

    public boolean hasPieceInDiagonal(final Position from, final Position to) {
        return hasRisingDiagonal(from, to) || hasDescendingDiagonal(from, to);
    }

    private void showColorStatus(final BiConsumer<String, Double> printScore, final Color color) {
        printScore.accept(color.getName(), calculateScore(color));
    }

    private double calculateScore(final Color color) {
        double pawnScore = calculateScorePawn(color);
        double otherScore = calculateScoreOtherPiece(color);

        return pawnScore + otherScore;
    }

    private double calculateScorePawn(final Color color) {
        return value.entrySet().stream()
                .filter(entry -> entry.getValue().isSameColor(color))
                .filter(entry -> entry.getValue().isPawn())
                .map(entry -> getPawnScore(entry.getKey(), entry.getValue(), color))
                .reduce(0.0, Double::sum);
    }

    private double getPawnScore(final Position position, final Piece pawn, final Color color) {
        long pawnCountInXAxis = value.entrySet().stream()
                .filter(entry -> entry.getValue().isSameColor(color))
                .filter(entry -> entry.getValue().isPawn())
                .filter(entry -> entry.getKey().getCoordinateX().equals(position.getCoordinateX()))
                .count();
        if (pawnCountInXAxis > 1) {
            return pawn.getScore() / 2;
        }
        return pawn.getScore();
    }

    private double calculateScoreOtherPiece(final Color color) {
        return value.values().stream()
                .filter(piece -> piece.isSameColor(color))
                .filter(piece -> !piece.isPawn())
                .map(Piece::getScore)
                .reduce(0.0, Double::sum);
    }

    private boolean hasRisingDiagonal(final Position from, final Position to) {
        int minX = CoordinateX.min(from.getCoordinateX(), to.getCoordinateX());
        int maxX = CoordinateX.max(from.getCoordinateX(), to.getCoordinateX());
        int minY = Math.min(from.getCoordinateY(), to.getCoordinateY());

        int y = minY + NEXT;
        for (int x = minX + NEXT; x < maxX; x++, y++) {
            if (hasPiece(Position.of(CoordinateX.from(x), CoordinateY.from(y)))) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDescendingDiagonal(final Position from, final Position to) {
        int nextY = Math.min(from.getCoordinateY(), to.getCoordinateY()) + NEXT;
        int maxY = Math.max(from.getCoordinateY(), to.getCoordinateY());
        int x = CoordinateX.max(from.getCoordinateX(), to.getCoordinateX()) - NEXT;

        for (int y = nextY; y < maxY; y++, x--) {
            if (hasPiece(Position.of(CoordinateX.from(x), CoordinateY.from(y)))) {
                return true;
            }
        }
        return false;
    }

    public boolean hasKing(final Color color) {
        return value.values().stream()
                .filter(piece -> piece.isSameColor(color))
                .anyMatch(Piece::isKing);
    }

    public boolean hasPiece(final Position position) {
        return value.get(position) != null;
    }

    public Piece getPiece(final Position position) {
        final Piece piece = value.get(position);
        if (piece != null) {
            return piece;
        }
        throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
    }

    public Map<Position, Piece> toMap() {
        return Collections.unmodifiableMap(value);
    }
}
