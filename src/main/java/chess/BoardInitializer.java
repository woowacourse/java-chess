package chess;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.Arrays;

public class BoardInitializer {

    public Board generateBoard() {
        Board board = new Board();
        putPawn(board);
        putRook(board);
        putKnight(board);
        putBishop(board);
        putQueen(board);
        putKing(board);
        return board;
    }

    private static void putKing(Board board) {
        board.put(new King(new Position(Row.EIGHT, Column.D), board, Color.BLACK));
        board.put(new King(new Position(Row.ONE, Column.D), board, Color.WHITE));
    }

    private static void putQueen(Board board) {
        board.put(new Queen(new Position(Row.EIGHT, Column.E), board, Color.BLACK));
        board.put(new Queen(new Position(Row.ONE, Column.E), board, Color.WHITE));
    }

    private static void putBishop(Board board) {
        board.put(new Bishop(new Position(Row.EIGHT, Column.C), board, Color.BLACK));
        board.put(new Bishop(new Position(Row.ONE, Column.C), board, Color.WHITE));
        board.put(new Bishop(new Position(Row.EIGHT, Column.F), board, Color.BLACK));
        board.put(new Bishop(new Position(Row.ONE, Column.F), board, Color.WHITE));
    }

    private static void putKnight(Board board) {
        board.put(new Knight(new Position(Row.EIGHT, Column.B), board, Color.BLACK));
        board.put(new Knight(new Position(Row.ONE, Column.B), board, Color.WHITE));
        board.put(new Knight(new Position(Row.EIGHT, Column.G), board, Color.BLACK));
        board.put(new Knight(new Position(Row.ONE, Column.G), board, Color.WHITE));
    }

    private static void putRook(Board board) {
        board.put(new Rook(new Position(Row.EIGHT, Column.A), board, Color.BLACK));
        board.put(new Rook(new Position(Row.ONE, Column.A), board, Color.WHITE));
        board.put(new Rook(new Position(Row.EIGHT, Column.H), board, Color.BLACK));
        board.put(new Rook(new Position(Row.ONE, Column.H), board, Color.WHITE));
    }

    private void putPawn(Board board) {
        Arrays.stream(Column.values()).forEach(column -> {
            board.put(new Pawn(new Position(Row.SEVEN, column), board, Color.BLACK));
            board.put(new Pawn(new Position(Row.TWO, column), board, Color.WHITE));
        });
    }
}
