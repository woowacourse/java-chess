package util;

import domain.Turn;
import domain.piece.Piece;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScoreCalculator {
    public static float calculate(List<List<Piece>> board, Turn turn) {
        return calculateBoard(switchColumnsAndRows(board), turn);
    }

    private static float calculateBoard(List<List<Piece>> board, Turn turn) {
        return (float) board.stream()
                .mapToDouble(column -> calculateLine(column, turn))
                .sum();
    }

    private static float calculateLine(List<Piece> line, Turn turn) {
        return (float) line.stream()
                .mapToDouble(piece -> calculatePieceScore(piece, line, turn))
                .sum();
    }

    private static float calculatePieceScore(Piece piece, List<Piece> line, Turn turn) {
        if (piece.isTurnOf(turn)) {
            return piece.getScore(line);
        }
        return 0f;
    }

    private static List<List<Piece>> switchColumnsAndRows(List<List<Piece>> board) {
        return IntStream.range(0, board.get(0).size())
                .mapToObj(index -> board.stream()
                        .map(list -> list.get(index))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
