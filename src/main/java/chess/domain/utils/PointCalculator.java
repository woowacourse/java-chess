package chess.domain.utils;

import chess.domain.board.Position;
import chess.domain.board.Team;
import chess.domain.piece.Piece;

import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class PointCalculator {
    public static double calculate(Map<Position, Piece> chessBoard, Team team) {
        return chessBoard.values().stream()
                .filter(piece -> piece != null && piece.getTeam() == team)
                .mapToDouble(Piece::getPoint)
                .sum();
    }


    public static double calculatePawn(Map<Position, Piece> chessBoard, Team team) {
        Map<Integer, Long> collect = groupFileAndPawnCount(chessBoard, team);
        return sumPawnCountAtSameFile(collect) * 0.5;
    }

    private static long sumPawnCountAtSameFile(Map<Integer, Long> collect) {
        return collect.values().stream()
                .filter(value -> value > 1)
                .mapToLong(value -> value)
                .sum();
    }

    private static Map<Integer, Long> groupFileAndPawnCount(Map<Position, Piece> chessBoard,
                                                            Team team) {
        return chessBoard.entrySet().stream()
                .filter(entry -> entry != null && entry.getValue().getTeam() == team)
                .filter(entry -> entry.getValue().isPawn())
                .map(entry -> entry.getKey().getX())
                .collect(groupingBy(Function.identity(), counting()));
    }
}
