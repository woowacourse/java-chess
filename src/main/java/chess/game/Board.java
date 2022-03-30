package chess.game;

import static chess.piece.detail.Color.BLACK;
import static chess.piece.detail.Color.WHITE;

import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.detail.Color;
import chess.piece.detail.Direction;
import chess.position.Column;
import chess.position.Position;
import java.util.*;

public class Board {

    private final Map<Position, Piece> value;

    private Board(final Map<Position, Piece> value) {
        this.value = new HashMap<>(value);
    }

    public static Board create() {
        return new Board(BoardInitializer.init());
    }

    public Map<Position, Piece> getValue() {
        return value;
    }

    public void move(final MoveCommand moveCommand, Color color) {
        final Position from = moveCommand.getFrom();
        final Position to = moveCommand.getTo();
        final Piece piece = getIfExist(from)
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 말이 존재하지 않습니다."));
        validateSameTeam(from, to);
        validateColor(piece, color);
        validateMove(moveCommand, piece);

        movePiece(from, to, piece);
    }

    public boolean isKingDead() {
        int kingCount = (int) value.values().stream()
                .filter(Piece::isKing)
                .count();

        return kingCount == 1;
    }

    public Map<Color, Double> getBoardScore() {
        final Map<Color, Double> score = new EnumMap<>(Color.class);
        score.put(BLACK, calculateScore(BLACK));
        score.put(WHITE, calculateScore(WHITE));
        return score;
    }

    private Optional<Piece> getIfExist(Position from) {
        if (!value.containsKey(from)) {
            return Optional.empty();
        }
        return Optional.of(value.get(from));
    }

    private void validateSameTeam(final Position from, final Position to) {
        if (value.containsKey(to) && value.get(from).isSameTeam(value.get(to))) {
            throw new IllegalArgumentException("이동할 위치에 같은색의 말이 존재합니다.");
        }
    }

    private void validateColor(final Piece piece, Color color) {
        if (piece.getColor() != color) {
            throw new IllegalArgumentException(color + "가 둘 차례입니다.");
        }
    }

    private void validateMove(final MoveCommand moveCommand, final Piece piece) {
        if (!piece.canMove(moveCommand)) {
            throw new IllegalArgumentException("해당 말이 갈 수 있는 위치가 아닙니다.");
        }
    }

    private void movePiece(final Position from, final Position to, final Piece piece) {
        validatePawnMove(from, to, piece);
        validatePiece(from, to, piece);
        value.put(to, piece);
        value.remove(from);
    }

    private void validatePawnMove(final Position from, final Position to, final Piece piece) {
        if (piece.isPawn()) {
            validatePawnForwardMove(from, to);
            validatePawnDiagonalMove(from, to);
        }
    }

    private void validatePawnForwardMove(final Position from, final Position to) {
        if (from.isSameColumn(to) && value.containsKey(to)) {
            throw new IllegalArgumentException("폰은 상대말이 있을 때 직진할 수 없습니다.");
        }
    }

    private void validatePawnDiagonalMove(final Position from, final Position to) {
        if (!from.isSameColumn(to) && !value.containsKey(to)) {
            throw new IllegalArgumentException("폰은 상대말이 존재하지 않을 때 대각선으로 이동할 수 없습니다.");
        }
    }

    private void validatePiece(final Position from, final Position to, final Piece piece) {
        if (!piece.isKnight() && !piece.isPawn()) {
            validatePieceBlock(from, to, piece);
        }
    }

    private void validatePieceBlock(final Position from, final Position to, final Piece piece) {
        final Direction direction = piece.getDirection(from, to);

        Position movedPosition = from;
        movedPosition = movedPosition.shift(direction);
        while (!movedPosition.equals(to)) {
            validateBlock(movedPosition);
            movedPosition = movedPosition.shift(direction);
        }
    }

    private void validateBlock(final Position position) {
        if (value.containsKey(position)) {
            throw new IllegalArgumentException("말의 이동 위치가 가로막혀 있습니다.");
        }
    }

    private double calculateScore(final Color color) {
        final double sum = value.values().stream()
                .filter(piece -> piece.getColor() == color)
                .mapToDouble(Piece::getScore)
                .sum();

        return sum - pawnCountOnSameColumn(color) * Pawn.REDUCED_PAWN_SCORE;
    }

    private double pawnCountOnSameColumn(final Color color) {
        return Arrays.stream(Column.values())
                .mapToInt(column -> countPawnsByColumn(column.getValue(), color))
                .filter(count -> count > 1)
                .sum();
    }

    private int countPawnsByColumn(final int column, Color color) {
        return (int) value.keySet().stream()
                .filter(position -> position.equalsColumn(column))
                .map(value::get)
                .filter(piece -> piece.isPawn() && piece.getColor().hasSameColor(color))
                .count();
    }

    @Override
    public String toString() {
        return "Board{" +
                "value=" + value +
                '}';
    }
}
