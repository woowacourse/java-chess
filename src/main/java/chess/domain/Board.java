package chess.domain;

import chess.cache.BoardCache;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Board {
    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board from(Map<Position, Piece> piece) {
        final Map<Position, Piece> board = new HashMap<>();
        board.putAll(BoardCache.create());
        board.putAll(piece);
        return new Board(board);
    }

    public Piece move(final Position source, final Position target, final Color color) {
        final Piece sourcePiece = board.get(source);
        final Piece targetPiece = board.get(target);

        validateInvalidColor(color, sourcePiece);
        validateInvalidMove(source, target, sourcePiece, targetPiece);

        board.put(source, Empty.create());
        board.put(target, sourcePiece);
        return targetPiece;
    }

    private void validateInvalidColor(final Color currentColor, final Piece sourcePiece) {
        if (sourcePiece.isNotSameColor(currentColor)) {
            throw new IllegalArgumentException("자신의 기물을 선택해야 합니다.");
        }
    }

    private void validateInvalidMove(final Position source, final Position target, final Piece sourcePiece, final Piece targetPiece) {
        final List<Position> positions = sourcePiece.findMoveAblePositions(source, target, targetPiece);

        final boolean isNotMoveAble = positions.subList(0, positions.indexOf(target))
                .stream()
                .anyMatch(this::isBlocked);

        if (isNotMoveAble) {
            throw new IllegalArgumentException("이동 위치가 다른 기물에 의해 막혀 있습니다.");
        }
    }

    private boolean isBlocked(final Position position) {
        return board.getOrDefault(position, Empty.create()).isNotSamePieceType(PieceType.EMPTY);
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }

    public double calculateScore(Color color) {
        return Arrays.stream(Row.values())
                .flatMap(row -> calculateColumnScore(color, row))
                .mapToDouble(Map.Entry::getValue)
                .sum();
    }

    private Stream<Map.Entry<PieceType, Double>> calculateColumnScore(final Color color, final Row row) {
        Map<PieceType, Double> columnScore = Arrays.stream(Column.values())
                .map(column -> board.get(Position.of(row, column)))
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
}
