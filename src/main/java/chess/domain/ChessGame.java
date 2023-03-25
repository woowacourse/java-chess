package chess.domain;

import chess.cache.PieceCache;
import chess.domain.piece.Piece;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessGame {
    private final Board board;
    private final Color color;

    private ChessGame(final Board board, final Color color) {
        this.board = board;
        this.color = color;
    }

    public static ChessGame create() {
        return new ChessGame(Board.from(PieceCache.create()), Color.WHITE);
    }

    public static ChessGame restart(Board board, Color color) {
        return new ChessGame(board, color);
    }

    public ChessGame next() {
        if (color == Color.WHITE) {
            return new ChessGame(board, Color.BLACK);
        }
        return new ChessGame(board, Color.WHITE);
    }

    public Piece move(final Position source, final Position target) {
        return board.move(source, target, color);
    }

    public double calculateScore(Color color) {
        return Arrays.stream(Row.values())
                .flatMap(row -> calculateColumnScore(color, row))
                .mapToDouble(Map.Entry::getValue)
                .sum();
    }

    private Stream<Map.Entry<PieceType, Double>> calculateColumnScore(final Color color, final Row row) {
        Map<PieceType, Double> columnScore = Arrays.stream(Column.values())
                .map(column -> board.getPiece(Position.of(row, column)))
                .filter(piece -> piece.isSameColor(color))
                .collect(Collectors.groupingBy(Piece::getPieceType, Collectors.summingDouble(Piece::getScore)));

        return columnScore.entrySet()
                .stream()
                .peek(this::calculatePawnScore);
    }

    private void calculatePawnScore(final Map.Entry<PieceType, Double> entry) {
        final double duplicatesPawnScore = 0.5;

        if (isVerticalDuplicatesPawn(entry)) {
            entry.setValue(entry.getValue() * duplicatesPawnScore);
        }
    }

    private boolean isVerticalDuplicatesPawn(final Map.Entry<PieceType, Double> entry) {
        final int pawnCount = 1;

        return entry.getKey() == PieceType.PAWN && entry.getValue() > pawnCount;
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    public Color getColor() {
        return color;
    }
}
