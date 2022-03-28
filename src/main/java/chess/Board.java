package chess;

import static chess.Position.of;
import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;

import chess.piece.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> value;

    private Board(final Map<Position, Piece> value) {
        this.value = new HashMap<>(value);
    }

    public static Board create() {
        final Map<Position, Piece> board = new HashMap<>();
        createKings(board);
        createQueen(board);
        createBishop(board);
        createKnight(board);
        createRook(board);
        createPawn(board);

        return new Board(board);
    }

    private static void createKings(final Map<Position, Piece> board) {
        board.put(of("e", 1), new King(WHITE));
        board.put(of("e", 8), new King(BLACK));
    }

    private static void createQueen(final Map<Position, Piece> board) {
        board.put(of("d", 1), new Queen(WHITE));
        board.put(of("d", 8), new Queen(BLACK));
    }

    private static void createBishop(final Map<Position, Piece> board) {
        board.put(of("c", 1), new Bishop(WHITE));
        board.put(of("f", 1), new Bishop(WHITE));
        board.put(of("c", 8), new Bishop(BLACK));
        board.put(of("f", 8), new Bishop(BLACK));
    }

    private static void createKnight(final Map<Position, Piece> board) {
        board.put(of("b", 1), new Knight(WHITE));
        board.put(of("g", 1), new Knight(WHITE));
        board.put(of("b", 8), new Knight(BLACK));
        board.put(of("g", 8), new Knight(BLACK));
    }

    private static void createRook(final Map<Position, Piece> board) {
        board.put(of("a", 1), new Rook(WHITE));
        board.put(of("h", 1), new Rook(WHITE));
        board.put(of("a", 8), new Rook(BLACK));
        board.put(of("h", 8), new Rook(BLACK));
    }

    private static void createPawn(final Map<Position, Piece> board) {
        for (final Column column : Column.values()) {
            board.put(of(column, Row.of(2)), new Pawn(WHITE));
            board.put(of(column, Row.of(7)), new Pawn(BLACK));
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

    public void move(final MoveCommand moveCommand, Color color) {
        final Position from = moveCommand.getFrom();
        final Position to = moveCommand.getTo();

        validatePieceExist(from);
        validateSameTeam(from, to);

        final Piece piece = value.get(from);
        validateColor(piece, color);
        validatePawnMove(moveCommand, from, to, piece);

        if (piece.canMove(moveCommand)) {
            validatePiece(from, to, piece);
            value.put(to, piece);
            value.remove(from);
        }
    }

    public Map<Color, Double> getBoardScore() {
        final Map<Color, Double> score = new HashMap<>();
        score.put(BLACK, calculateScore(BLACK));
        score.put(WHITE, calculateScore(WHITE));
        return score;
    }

    public double calculateScore(final Color color) {
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

    public boolean isKingDead() {
        int kingCount = (int) value.values().stream()
                .filter(Piece::isKing)
                .count();

        return kingCount == 1;
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

    private void validateColor(final Piece piece, Color color) {
        if (piece.getColor() != color) {
            throw new IllegalArgumentException(color + "가 둘 차례입니다.");
        }
    }

    private void validatePawnMove(final MoveCommand moveCommand, final Position from, final Position to, final Piece piece) {
        if (piece.isPawn() && piece.canMove(moveCommand)) {
            validatePawnForwardMove(from, to);
            validatePawnDiagonalMove(from, to);
        }
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

    private void validateBlock(final Position position) {
        if (value.containsKey(position)) {
            throw new IllegalArgumentException("말의 이동 위치가 가로막혀 있습니다.");
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
}
