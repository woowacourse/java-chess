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
        List<Rank> chessBoard1 = chessBoard;
        double sum = chessBoard1.stream()
                .flatMap(rank -> rank.getRank().stream())
                .filter(square -> square.isNotSameType(EmptyType.EMPTY) && square.isSameColor(color))
                .mapToDouble(square -> square.getSquareStatus().getScore())
                .sum();

        return sum - diffScore(chessBoard1, color);
    }

    private double diffScore(List<Rank> chessBoard1, Color color) {
        Map<Integer, Integer> numberOfPawnInColumn = new HashMap<>();
        for (Rank value : chessBoard1) {
            List<Square> rank = value.getRank();
            forEachColumn(numberOfPawnInColumn, rank, color);
        }
        return numberOfPawnInColumn.values()
                .stream()
                .filter(i -> 2 <= i)
                .mapToDouble(i -> i * 0.5)
                .sum();
    }

    private void forEachColumn(Map<Integer, Integer> numberOfPawnInColumn, List<Square> rank, Color color) {
        for (int column = 0; column < rank.size(); column++) {
            putIfExistPawn(numberOfPawnInColumn, rank, column, color);
        }
    }

    private void putIfExistPawn(Map<Integer, Integer> numberOfPawnInColumn, List<Square> rank, int column, Color color) {
        Square square = rank.get(column);

        if (square.isSameType(PieceType.PAWN) && square.isSameColor(color)) {
            numberOfPawnInColumn.put(column,
                    numberOfPawnInColumn.getOrDefault(column, 0) + 1);
        }
    }

    public boolean isCheckMate() {
        return chessBoard.stream()
                .flatMap(rank -> rank.getRank().stream())
                .filter(square -> square.isSameType(PieceType.KING))
                .count() != 2;
    }

}
