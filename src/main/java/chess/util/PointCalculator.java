package chess.util;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.Map;
import java.util.function.Function;

public class PointCalculator {
    public static double calculateSumAllPiecesPoint(Map<Position, Piece> chessBoard, Team team) {
        return chessBoard.values().stream()
            .filter(piece -> piece.getTeam() == team)
            .mapToDouble(Piece::getPoint)
            .sum();
    }


    public static double calculateSumPawnAtSameFilePoint(Map<Position, Piece> chessBoard,
        Team team) {
        Map<Integer, Long> collect = groupFileOnlyPawnCount(chessBoard, team);
        return sumPawnCountAtSameFile(collect) * 0.5;
    }

    private static long sumPawnCountAtSameFile(Map<Integer, Long> collect) {
        return collect.values().stream()
            .filter(value -> value > 1)
            .mapToLong(value -> value)
            .sum();
    }

    private static Map<Integer, Long> groupFileOnlyPawnCount(Map<Position, Piece> chessBoard,
        Team team) {
        return chessBoard.entrySet().stream()
            .filter(entry -> entry.getValue().getTeam() == team)
            .filter(entry -> entry.getValue().isPawn())
            .map(entry -> entry.getKey().getX())
            .collect(groupingBy(Function.identity(), counting()));
    }
}
