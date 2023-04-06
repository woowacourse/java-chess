package util;

import domain.Turn;
import domain.piece.Piece;
import domain.piece.pawn.*;

import java.util.Collections;
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
        validatePawnsDuplicatedOnColumn(line);
        return (float) line.stream()
                .mapToDouble(piece -> calculatePieceScore(piece, turn))
                .sum();
    }

    private static void validatePawnsDuplicatedOnColumn(List<Piece> column) {
        validateBlackPawnsDuplicatedOnColumn(column);
        validateWhitePawnsDuplicatedOnColumn(column);
    }

    private static void validateBlackPawnsDuplicatedOnColumn(List<Piece> column) {
        long blackPawnCountOnColumn = column.stream()
                .filter(Piece::isBlackPawn)
                .count();
        if (arePawnsDuplicatingOnColumn(blackPawnCountOnColumn)) {
            Collections.replaceAll(column, new BlackPawn(), new DuplicatedOnColumnBlackPawn());
            Collections.replaceAll(column, new OnceMovedBlackPawn(), new DuplicatedOnColumnBlackPawn());
        }
    }

    private static void validateWhitePawnsDuplicatedOnColumn(List<Piece> column) {
        long whitePawnCountOnColumn = column.stream()
                .filter(Piece::isWhitePawn)
                .count();
        if (arePawnsDuplicatingOnColumn(whitePawnCountOnColumn)) {
            Collections.replaceAll(column, new WhitePawn(), new DuplicatedOnColumnWhitePawn());
            Collections.replaceAll(column, new OnceMovedWhitePawn(), new DuplicatedOnColumnWhitePawn());
        }
    }

    private static boolean arePawnsDuplicatingOnColumn(long pawnsCount) {
        return 2 <= pawnsCount;
    }

    private static float calculatePieceScore(Piece piece, Turn turn) {
        if (turn.isBlack() && piece.isBlack()) {
            return piece.getScore();
        }
        if (turn.isWhite() && piece.isWhite()) {
            return piece.getScore();
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
