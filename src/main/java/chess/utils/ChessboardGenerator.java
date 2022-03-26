package chess.utils;

import chess.piece.*;

import java.util.ArrayList;
import java.util.List;

public class ChessboardGenerator {

    private static final int SIZE_OF_BOARD = 8;

    public static List<List<Piece>> generate() {
        List<List<Piece>> board = new ArrayList<>();

        board.add(withoutPawn(Color.BLACK));
        board.add(pawn(Color.BLACK));

        for (int i = 0; i < 4; i++) {
            board.add(blank());
        }

        board.add(pawn(Color.WHITE));
        board.add(withoutPawn(Color.WHITE));

        return board;
    }

    private static List<Piece> withoutPawn(Color color) {
        List<Piece> line = new ArrayList<>();
        line.add(new Rook(color));
        line.add(new Knight(color));
        line.add(new Bishop(color));
        line.add(new Queen(color));
        line.add(new King(color));
        line.add(new Bishop(color));
        line.add(new Knight(color));
        line.add(new Rook(color));

        return line;
    }

    private static List<Piece> pawn(Color color) {
        List<Piece> line = new ArrayList<>();

        for (int i = 0; i < SIZE_OF_BOARD; i++) {
            line.add(new Pawn(color));
        }

        return line;
    }

    private static List<Piece> blank() {
        List<Piece> line = new ArrayList<>();

        for (int i = 0; i < SIZE_OF_BOARD; i++) {
            line.add(new Blank());
        }

        return line;
    }
}
