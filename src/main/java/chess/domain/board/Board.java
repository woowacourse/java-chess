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

        if (!piece.isKnight() && hasPieceInPath(from, to)) {
            throw new IllegalArgumentException("이동 경로에 기물이 존재합니다.");
        }
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

    private boolean hasPieceInPath(final Position from, final Position to) {
        Position next = nextPosition(from, to);
        while (next != to) {
            if (hasPiece(next)) {
                return true;
            }
            next = nextPosition(next, to);
        }
        return false;
    }

    private Position nextPosition(final Position from, final Position to) {
        return from.next(to);
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
