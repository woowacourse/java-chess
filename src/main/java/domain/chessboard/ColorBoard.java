package domain.chessboard;

import domain.piece.Color;
import domain.piece.PieceType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColorBoard {

    private final Color color;
    private final ChessBoard chessBoard;

    public ColorBoard(Color color, ChessBoard chessBoard) {
        this.color = color;
        this.chessBoard = chessBoard;
    }
    
    public double calculateScore() {
        List<Rank> chessBoard1 = chessBoard.getChessBoard();
        double sum = chessBoard1.stream()
                .flatMap(rank -> rank.getRank().stream())
                .filter(square -> square.isNotSameType(EmptyType.EMPTY) && square.isSameColor(color))
                .mapToDouble(square -> square.getSquareStatus().getScore())
                .sum();

        return sum - diffScore(chessBoard1);
    }

    private double diffScore(List<Rank> chessBoard1) {
        Map<Integer, Integer> numberOfPawnInColumn = new HashMap<>();
        for (Rank value : chessBoard1) {
            List<Square> rank = value.getRank();
            forEachColumn(numberOfPawnInColumn, rank);
        }
        return numberOfPawnInColumn.values()
                .stream()
                .filter(i -> 2 <= i)
                .mapToDouble(i -> i * 0.5)
                .sum();
    }

    public long countKing() {
        return chessBoard.getChessBoard()
                .stream()
                .flatMap(rank -> rank.getRank().stream())
                .filter(square -> square.isSameType(PieceType.KING) && square.isSameColor(color))
                .count();
    }

    public boolean isExistKing() {
        return chessBoard.getChessBoard()
                .stream()
                .flatMap(rank -> rank.getRank().stream())
                .anyMatch(square -> square.isSameType(PieceType.KING) && square.isSameColor(color));
    }

    private void forEachColumn(Map<Integer, Integer> numberOfPawnInColumn, List<Square> rank) {
        for (int column = 0; column < rank.size(); column++) {
            putIfExistPawn(numberOfPawnInColumn, rank, column);
        }
    }

    private void putIfExistPawn(Map<Integer, Integer> numberOfPawnInColumn, List<Square> rank, int column) {
        Square square = rank.get(column);
        
        if (square.isSameType(PieceType.PAWN) && square.isSameColor(color)) {
            numberOfPawnInColumn.put(column, 
                    numberOfPawnInColumn.getOrDefault(column, 0) + 1);
        }
    }

}
