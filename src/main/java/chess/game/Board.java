package chess.game;

import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;

import chess.piece.Color;
import chess.piece.Pawn;
import chess.piece.Piece;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private static final int DEAD_KING_COUNT = 1;
    private static final int PAWN_MINIMUM_REDUCE_ROW_COUNT = 1;

    private final Map<Position, Piece> value;

    public Board(final Map<Position, Piece> value) {
        this.value = new HashMap<>(value);
    }

    public void move(final MoveCommand moveCommand, final Color color) {
        final Position from = moveCommand.getFrom();
        final Position to = moveCommand.getTo();
        final Piece piece = value.get(from);
        validateMove(color, from, to, piece);
        movePiece(from, to, piece);
    }

    public Map<Color, Double> getBoardScore() {
        final Map<Color, Double> score = new HashMap<>();
        score.put(BLACK, calculateScore(BLACK));
        score.put(WHITE, calculateScore(WHITE));
        return score;
    }

    public double calculateScore(final Color color) {
        return sumScore(color) - pawnCountOnSameColumn(color) * Pawn.REDUCED_SCORE;
    }

    public boolean isKingDead() {
        return countKingPiece() == DEAD_KING_COUNT;
    }

    private void movePiece(final Position from, final Position to, final Piece piece) {
        if (piece.canMove(from, to)) {
            value.put(to, piece);
            value.remove(from);
            return;
        }
        throw new IllegalArgumentException("이동이 불가능 합니다.");
    }

    private double sumScore(final Color color) {
        return value.values().stream()
                .filter(piece -> piece.getColor() == color)
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double pawnCountOnSameColumn(final Color color) {
        return Arrays.stream(Column.values())
                .mapToInt(column -> countPawnsByColumn(column.getValue(), color))
                .filter(count -> count > PAWN_MINIMUM_REDUCE_ROW_COUNT)
                .sum();
    }

    private int countPawnsByColumn(final int column, final Color color) {
        return (int) value.keySet().stream()
                .filter(position -> position.equalsColumn(column))
                .map(value::get)
                .filter(piece -> piece.isPawn() && piece.getColor().hasSameColor(color))
                .count();
    }

    private int countKingPiece() {
        return (int) value.values().stream()
                .filter(Piece::isKing)
                .count();
    }

    private void validateMove(final Color color, final Position from, final Position to, final Piece piece) {
        validatePieceExist(from);
        validateSameTeam(from, to);
        validatePiece(from, to, piece);
        validateColor(piece, color);
        validatePawnMove(from, to, piece);
    }

    private void validatePieceExist(final Position position) {
        if (!value.containsKey(position)) {
            throw new IllegalArgumentException("해당 위치에 말이 존재하지 않습니다.");
        }
    }

    private void validateSameTeam(final Position from, final Position to) {
        if (value.containsKey(to) && value.get(from).isSameTeam(value.get(to))) {
            throw new IllegalArgumentException("이동할 위치에 같은색의 말이 존재합니다.");
        }
    }

    private void validatePiece(final Position from, final Position to, final Piece piece) {
        if (!piece.isKnight()) {
            validatePieceBlock(from, to);
        }
    }

    private void validatePieceBlock(final Position from, final Position to) {
        final Direction direction = from.findDirection(to);
        Position movedPosition = from.shift(direction);
        while (!movedPosition.equals(to)) {
            validateBlock(movedPosition);
            movedPosition = movedPosition.shift(direction);
        }
    }

    private void validateColor(final Piece piece, final Color color) {
        if (piece.getColor() != color) {
            throw new IllegalArgumentException(color + "가 둘 차례입니다.");
        }
    }

    private void validatePawnMove(final Position from, final Position to, final Piece piece) {
        if (piece.isPawn()) {
            validatePawnForwardMove(from, to);
        }
    }

    private void validateBlock(final Position position) {
        if (value.containsKey(position)) {
            throw new IllegalArgumentException("말의 이동 위치가 가로막혀 있습니다.");
        }
    }

    private void validatePawnForwardMove(final Position from, final Position to) {
        if (from.isSameColumn(to) && value.containsKey(to)) {
            throw new IllegalArgumentException("폰은 상대말이 있을 때 직진할 수 없습니다.");
        }
        if (!from.isSameColumn(to) && !value.containsKey(to)) {
            throw new IllegalArgumentException("폰은 상대말이 존재하지 않을 때 대각선으로 이동할 수 없습니다.");
        }
    }

    public Map<Position, Piece> getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Board{" +
                "value=" + value +
                '}';
    }
}
