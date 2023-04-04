package domain.util;

import domain.Turn;
import domain.piece.Piece;
import domain.piece.pawn.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.Turn.*;

public class ScoreCalculator {
    public static Map<Turn, Float> calculate(List<List<Piece>> board) {
        Map<Turn, Float> score = new HashMap<>();
        score.put(BLACK, 0f);
        score.put(WHITE, 0f);

        calculateBoard(switchColumnsAndRows(board), score);

        return score;
    }

    private static void calculateBoard(List<List<Piece>> board, Map<Turn, Float> score) {
        for (List<Piece> column : board) {
            calculateLine(score, column);
        }
    }

    private static void calculateLine(Map<Turn, Float> score, List<Piece> column) {
        validatePawnsDuplicatedOnColumn(column);

        for (Piece piece : column) {
            calculatePieceScore(score, piece);
        }
    }

    private static void validatePawnsDuplicatedOnColumn(List<Piece> column) {
        validateBlackPawnsDuplicatedOnColumn(column);
        validateWhitePawnsDuplicatedOnColumn(column);
    }

    private static void validateBlackPawnsDuplicatedOnColumn(List<Piece> column) {
        long blackPawnCountOnColumn = column.stream()
                .filter(Piece::isBlackPawn)
                .count();

        if (2 <= blackPawnCountOnColumn) {
            Collections.replaceAll(column, new BlackPawn(), new DuplicatedOnColumnBlackPawn());
            Collections.replaceAll(column, new OnceMovedBlackPawn(), new DuplicatedOnColumnBlackPawn());
        }
    }

    private static void validateWhitePawnsDuplicatedOnColumn(List<Piece> column) {
        long whitePawnCountOnColumn = column.stream()
                .filter(Piece::isWhitePawn)
                .count();

        if (2 <= whitePawnCountOnColumn) {
            Collections.replaceAll(column, new WhitePawn(), new DuplicatedOnColumnWhitePawn());
            Collections.replaceAll(column, new OnceMovedWhitePawn(), new DuplicatedOnColumnWhitePawn());
        }
    }

    private static void calculatePieceScore(Map<Turn, Float> score, Piece piece) {
        if (piece.isBlack()) {
            score.put(BLACK, score.get(BLACK) + piece.getScore());
        }
        if (piece.isWhite()) {
            score.put(WHITE, score.get(WHITE) + piece.getScore());
        }
    }

    private static List<List<Piece>> switchColumnsAndRows(List<List<Piece>> board) {
        return IntStream.range(0, board.get(0).size())
                .mapToObj(index -> board.stream()
                        .map(list -> list.get(index))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
