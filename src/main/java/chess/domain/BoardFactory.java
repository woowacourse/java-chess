package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class BoardFactory {

    public static Map<Point, Piece> createEmptyBoard() {
        Map<Point, Piece> board = new HashMap<>();

        IntStream.rangeClosed(1, 8)
                .boxed()
                .map(BoardFactory::lineOfEmpty)
                .forEach(board::putAll);

        return board;
    }

    public static Map<Point, Piece> createInitialChessBoard() {
        Map<Point, Piece> board = new HashMap<>();

        board.putAll(createEighthLine());
        board.putAll(createSeventhLine());
        IntStream.rangeClosed(3, 6)
                .boxed()
                .map(BoardFactory::lineOfEmpty)
                .forEach(board::putAll);
        board.putAll(createSecondLine());
        board.putAll(createFirstLine());
        return board;
    }

    private static Map<Point, Piece> createEighthLine() {
        return lineOfKing(8, Team.BLACK);
    }

    private static Map<Point, Piece> createSeventhLine() {
        return lineOfPawn(7, Team.BLACK);
    }

    private static Map<Point, Piece> createSecondLine() {
        return lineOfPawn(2, Team.WHITE);
    }

    private static Map<Point, Piece> createFirstLine() {
        return lineOfKing(1, Team.WHITE);
    }

    private static Map<Point, Piece> lineOfPawn(int rank, Team team) {
        Map<Point, Piece> line = new HashMap<>();

        for (char c = File.minValue(); c <= File.maxValue(); c++) {
            line.put(Point.of(File.of(c), Rank.of(rank)), Piece.pawnFrom(team));
        }
        return line;
    }

    private static Map<Point, Piece> lineOfKing(int rank, Team team) {
        return Map.of(
                Point.of(File.A, Rank.of(rank)), Piece.rookFrom(team),
                Point.of(File.B, Rank.of(rank)), Piece.knightFrom(team),
                Point.of(File.C, Rank.of(rank)), Piece.bishopFrom(team),
                Point.of(File.D, Rank.of(rank)), Piece.queenFrom(team),
                Point.of(File.E, Rank.of(rank)), Piece.kingFrom(team),
                Point.of(File.F, Rank.of(rank)), Piece.bishopFrom(team),
                Point.of(File.G, Rank.of(rank)), Piece.knightFrom(team),
                Point.of(File.H, Rank.of(rank)), Piece.rookFrom(team)
        );
    }

    private static Map<Point, Piece> lineOfEmpty(int rank) {
        Map<Point, Piece> line = new HashMap<>();

        for (char c = File.minValue(); c <= File.maxValue(); c++) {
            line.put(Point.of(File.of(c), Rank.of(rank)), Piece.empty());
        }
        return line;
    }
}
