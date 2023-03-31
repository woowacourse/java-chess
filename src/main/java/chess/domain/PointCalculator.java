package chess.domain;

import chess.domain.board.RowPieces;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Column;

import java.util.List;

public class PointCalculator {

    private static final double EXCLUDING_POINT_OF_SINGLE_PAWN = 0.5;

    private PointCalculator() {
    }

    public static double sumPoints(List<Double> points) {
        double sum = 0;
        for (double point : points) {
            sum += point;
        }
        return sum;
    }

    public static double totalExcludingPointsOfPawn(List<RowPieces> chessBoard, Team team) {
        double sum = 0;
        for (Column column : Column.values()) {
            sum += numberOfPawnInColumnIfPawnNumbersOver2(chessBoard, team, column);
        }
        return sum * EXCLUDING_POINT_OF_SINGLE_PAWN;
    }

    private static double numberOfPawnInColumnIfPawnNumbersOver2(List<RowPieces> chessBoard, Team team, Column column) {
        double count = 0;
        for (int i = 0; i < 8; i++) {
            count = addCountIfPawnExists(chessBoard, team, column, count, i);
        }
        if (count < 2) {
            return 0;
        }
        return count;
    }

    private static double addCountIfPawnExists(List<RowPieces> chessBoard, Team team, Column column, double count, int i) {
        if (chessBoard.get(i).checkPawnByColumn(column, team)) {
            count++;
        }
        return count;
    }

    public static Team winnerOf(double blackPoint, double whitePoint) {
        if (blackPoint > whitePoint) {
            return Team.BLACK;
        }
        if (blackPoint < whitePoint) {
            return Team.WHITE;
        }
        return Team.EMPTY;
    }

}
