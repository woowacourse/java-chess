package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public final class Board {
    private static final int TWO_KINGS_MEAN_NOT_FINISHED = 2;
    private static final int DISCOUNT_PAWN_CRITERIA = 1;
    private static final double DISCOUNT_UNIT = 0.5;

    private final Map<Position, Piece> board;

    Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public boolean move(Position from, Position to) {
        final Piece piece = board.getOrDefault(from, Piece.EMPTY);
        if (piece.movable(from, to, this)) {
            board.put(to, piece);
            board.remove(from);
            return true;
        }

        return false;
    }

    public boolean isFinished() {
        return board.values()
                .stream()
                .filter(Piece::isKing)
                .count() < TWO_KINGS_MEAN_NOT_FINISHED;
    }

    public boolean isSameColorOnTarget(Piece source, Position target) {
        return source.isSameColor(board.getOrDefault(target, Piece.EMPTY));
    }

    public boolean isEnemyOnTarget(Piece source, Position target) {
        return source.isEnemy(board.getOrDefault(target, Piece.EMPTY));
    }

    public boolean exists(Position position) {
        return board.containsKey(position);
    }

    public boolean isTurnOf(Position from, Color currentTurn) {
        return board.getOrDefault(from, Piece.EMPTY).isSameColor(currentTurn);
    }

    public Map<Color, Double> getScore() {
        final Map<Color, Double> scoreByColor = calculateRawSum();

        scoreByColor.computeIfPresent(Color.BLACK,
                (color, currentValue) -> currentValue + discountPawnsInSameFile(Color.BLACK));
        scoreByColor.computeIfPresent(Color.WHITE,
                (color, currentValue) -> currentValue + discountPawnsInSameFile(Color.WHITE));

        return scoreByColor;
    }

    private Map<Color, Double> calculateRawSum() {
        return board.values()
                .stream()
                .collect(Collectors.groupingBy(
                        Color::from,
                        () -> new EnumMap<>(Color.class),
                        Collectors.summingDouble(Piece::getScore)));
    }

    private Double discountPawnsInSameFile(Color color) {
        double discount = 0.0;

        for (File file : File.values()) {
            final long count = countPawnsInSameFile(color, file);
            discount = discount(discount, count);
        }

        return discount;
    }

    private long countPawnsInSameFile(Color color, File file) {
        return board.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isSameFile(file))
                .map(Entry::getValue)
                .filter(Piece::isPawn)
                .filter(piece -> piece.isSameColor(color))
                .count();
    }

    private double discount(double discount, long count) {
        if (count > DISCOUNT_PAWN_CRITERIA) {
            discount -= count * DISCOUNT_UNIT;
        }
        return discount;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
