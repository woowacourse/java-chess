package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.point.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class BoardFactory {

    public static Map<Point, Piece> createEmptyBoard() {
        final Map<Point, Piece> board = new HashMap<>();

        IntStream.rangeClosed(1, 8)
                .boxed()
                .map(BoardFactory::lineOfEmpty)
                .forEach(board::putAll);

        return board;
    }

    public static Map<Point, Piece> createInitialChessBoard() {
        final Map<Point, Piece> board = new HashMap<>();

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

    private static Map<Point, Piece> lineOfPawn(final int rank, final Team team) {
        final Map<Point, Piece> line = new HashMap<>();

        for (char c = 'a'; c <= 'h'; c++) {
            line.put(new Point(c, rank), new Pawn(team));
        }
        return line;
    }

    private static Map<Point, Piece> lineOfKing(final int rank, final Team team) {
        return Map.of(
                new Point('a', rank), new Rook(team),
                new Point('b', rank), new Knight(team),
                new Point('c', rank), new Bishop(team),
                new Point('d', rank), new Queen(team),
                new Point('e', rank), new King(team),
                new Point('f', rank), new Bishop(team),
                new Point('g', rank), new Knight(team),
                new Point('h', rank), new Rook(team)
        );
    }

    private static Map<Point, Piece> lineOfEmpty(final int rank) {
        final Map<Point, Piece> line = new HashMap<>();

        for (char c = 'a'; c <= 'h'; c++) {
            line.put(new Point(c, rank), Empty.INSTANCE);
        }
        return line;
    }
}
