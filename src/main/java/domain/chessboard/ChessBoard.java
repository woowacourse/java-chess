package domain.chessboard;

import domain.coordinate.Position;
import domain.piece.Color;
import domain.piece.PieceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private static final int NUMBER_OF_NONE_LINES = 4;
    private static final int NUMBER_OF_STANDARD_FOR_DECREASING_PAWN_SCORE = 2;
    private static final double PAWN_SCORE_IS_ON_SAME_COLUMN = 0.5;
    private static final int STANDARD_NUMBER_OF_CHECKMATE = 2;
    private static final int ADDITIONAL_COUNT_WHEN_FOUND_PAWN = 1;

    private final List<Rank> chessBoard;

    public ChessBoard(final List<Rank> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoard generate() {
        final List<Rank> chessBoard = new ArrayList<>();
        chessBoard.add(Rank.initRankToRank(RankFactory.OTHERS_BLACK));
        chessBoard.add(Rank.initRankToRank(RankFactory.PAWN_BLACK));
        for (int i = 0; i < NUMBER_OF_NONE_LINES; i++) {
            chessBoard.add(Rank.initRankToRank(RankFactory.EMPTY));
        }
        chessBoard.add(Rank.initRankToRank(RankFactory.PAWN_WHITE));
        chessBoard.add(Rank.initRankToRank(RankFactory.OTHERS_WHITE));

        return new ChessBoard(new ArrayList<>(chessBoard));
    }

    public List<Rank> getChessBoard() {
        return new ArrayList<>(chessBoard);
    }

    public Square findSquare(Position position) {
        return chessBoard.get(position.getY())
                .getRank()
                .get(position.getX());
    }

    public double calculateColorScore(Color color) {
        double sum = chessBoard.stream()
                .flatMap(rank -> rank.getRank().stream())
                .filter(square -> square.isNotSameType(EmptyType.EMPTY) && square.isSameColor(color))
                .mapToDouble(square -> square.getSquareStatus().getScore())
                .sum();

        return sum - diffScore(color);
    }

    public boolean isNotTwoKing() {
        return chessBoard.stream()
                .flatMap(rank -> rank.getRank().stream())
                .filter(square -> square.isSameType(PieceType.KING))
                .count() != STANDARD_NUMBER_OF_CHECKMATE;
    }

    public boolean isExistKingThisColor(Color color) {
        return chessBoard.stream()
                .flatMap(rank -> rank.getRank().stream())
                .anyMatch(square -> square.isSameType(PieceType.KING) && square.isSameColor(color));
    }

    private double diffScore(Color color) {
        Map<Integer, Integer> numberOfPawnInColumn = new HashMap<>();
        for (Rank value : chessBoard) {
            List<Square> rank = value.getRank();
            forEachColumn(numberOfPawnInColumn, rank, color);
        }
        return numberOfPawnInColumn.values()
                .stream()
                .filter(this::isNotOnlyInColumn)
                .mapToDouble(this::convertHalfScore)
                .sum();
    }

    private void forEachColumn(Map<Integer, Integer> numberOfPawnInColumn, List<Square> rank, Color color) {
        for (int column = 0; column < rank.size(); column++) {
            Square square = rank.get(column);
            boolean isPawnOfThisColor = isPawnOfThisColor(square, color);
            putIfExistPawn(numberOfPawnInColumn, column, isPawnOfThisColor);
        }
    }

    private boolean isPawnOfThisColor(Square square, Color color) {
        return square.isSameType(PieceType.PAWN) && square.isSameColor(color);
    }

    private void putIfExistPawn(Map<Integer, Integer> numberOfPawnInColumn, int column, boolean isPawn) {
        if (isPawn) {
            numberOfPawnInColumn.put(column
                    , numberOfPawnInColumn.getOrDefault(column, 0) + ADDITIONAL_COUNT_WHEN_FOUND_PAWN);
        }
    }

    private boolean isNotOnlyInColumn(Integer numberOfPawn) {
        return NUMBER_OF_STANDARD_FOR_DECREASING_PAWN_SCORE <= numberOfPawn;
    }

    private double convertHalfScore(Integer numberOfPawn) {
        return numberOfPawn * PAWN_SCORE_IS_ON_SAME_COLUMN;
    }

}
