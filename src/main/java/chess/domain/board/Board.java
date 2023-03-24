package chess.domain.board;

import chess.domain.piece.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Board {

    private static final String CAN_NOT_MOVE_EXCEPTION_MESSAGE = "유효한 움직임이 아닙니다.";

    private final List<Squares> board = new ArrayList<>();

    public Board() {
        board.add(Squares.initPieces(Color.WHITE));
        board.add(Squares.initWhitePawns());
        board.add(Squares.initEmpty());
        board.add(Squares.initEmpty());
        board.add(Squares.initEmpty());
        board.add(Squares.initEmpty());
        board.add(Squares.initBlackPawns());
        board.add(Squares.initPieces(Color.BLACK));
    }

    public void play(Position source, Position target, Color color) {
        Square sourceSquare = getSquare(source);
        Square targetSquare = getSquare(target);

        var movablePath = sourceSquare.computePath(source, target, targetSquare, color);
        var isEmptySquare = generateIsEmptySquare(movablePath);

        validateMove(source, target, sourceSquare, isEmptySquare);
        move(sourceSquare, targetSquare);
    }

    public boolean isKingDead(Color color) {
        return board.stream()
                .noneMatch(squares -> squares.findKing(color));
    }

    private void validateMove(final Position source, final Position target, final Square sourceSquare, final Map<Position, Boolean> isEmptySquare) {
        if (!sourceSquare.canMovePiece(isEmptySquare, source, target)) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }
    }

    private Map<Position, Boolean> generateIsEmptySquare(final Set<Position> movablePath) {
        return movablePath.stream()
                .collect(Collectors.toMap(
                        position -> position,
                        position -> getSquare(position).isEmpty()));
    }

    private void move(final Square sourceSquare, final Square targetSquare) {
        targetSquare.changePiece(sourceSquare);
    }

    private Square getSquare(final Position source) {
        int rank = source.getRank();
        int file = source.getFile();
        Squares squares = board.get(rank);
        return squares.get(file);
    }

    public List<Squares> getSquares() {
        return new ArrayList<>(board);
    }

    private double calculateFileScore(final Color color, final int file) {
        double sum = board.stream()
                .mapToDouble(squares -> squares.getScoreFromFile(color, file))
                .sum();

        return sum - calculatePawnScore(color, file);
    }

    private double calculatePawnScore(Color color, int file) {
        final int count = countPawn(color, file);
        if (count == 1) {
            return 0;
        }
        return count * 0.5;
    }

    public double calculateTotalScore(final Color color) {
        double sum = 0;
        for (int i = 0; i < board.size(); i++) {
            sum = sum + calculateFileScore(color, i);
        }

        return sum;
    }

    private int countPawn(final Color color, final int file) {
        return (int) board.stream()
                .filter(squares -> squares.hasPawnAtFile(color, file))
                .count();
    }
}
