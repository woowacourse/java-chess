package chess.domain;

import chess.domain.pieces.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class BoardCreator {

    private BoardCreator() {

    }

    public static Board create() {
        Map<Point, Piece> board = new HashMap<>();
        IntStream.rangeClosed(Point.MIN_AXIS, Point.MAX_AXIS)
                .forEach(i ->
                        IntStream.rangeClosed(Point.MIN_AXIS, Point.MAX_AXIS)
                                .forEach(j -> board.put(Point.get(i, j), new Empty())));
        white(board);
        black(board);
        return new Board(board);
    }

    private static void white(Map<Point, Piece> board) {
        ChessTeam team = ChessTeam.WHITE;
        pawn(board, team, 2);
        some(board, team, 1);
    }

    private static void black(Map<Point, Piece> board) {
        ChessTeam team = ChessTeam.BLACK;
        pawn(board, team, 7);
        some(board, team, 8);
    }

    private static void pawn(Map<Point, Piece> board, ChessTeam team, int position) {
        for (int i = Point.MIN_AXIS; i <= Point.MAX_AXIS; i++) {
            board.put(Point.get(i, position), new Pawn(team));
        }
    }

    private static void some(Map<Point, Piece> board, ChessTeam team, int position) {
        board.put(Point.get(1, position), new Rook(team));
        board.put(Point.get(2, position), new Knight(team));
        board.put(Point.get(3, position), new Bishop(team));
        board.put(Point.get(4, position), new Queen(team));
        board.put(Point.get(5, position), new King(team));
        board.put(Point.get(6, position), new Bishop(team));
        board.put(Point.get(7, position), new Knight(team));
        board.put(Point.get(8, position), new Rook(team));
    }
}
