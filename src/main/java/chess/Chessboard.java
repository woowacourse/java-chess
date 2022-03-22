package chess;

import chess.piece.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chessboard {

    List<List<Piece>> board;

    public static Chessboard emptyChessboard() {
        return new Chessboard(new ArrayList<>());
    }

    public static Chessboard initializedChessboard() {
        return new Chessboard();
    }

    private Chessboard(List<List<Piece>> board) {
        this.board = board;
    }

    private Chessboard() {
        board = new ArrayList<>();
        initializePieceWithoutPawn(Color.BLACK);
        initializePawn(Color.BLACK);

        for (int i = 0; i < 4; i++) {
            initializeBlank();
        }
        initializePawn(Color.WHITE);
        initializePieceWithoutPawn(Color.WHITE);
    }

    public List<List<Piece>> getBoard() {
        return Collections.unmodifiableList(board);
    }

    private void initializePieceWithoutPawn(Color color) {
        List<Piece> line = new ArrayList<>();
        line.add(new Rook(color));
        line.add(new Knight(color));
        line.add(new Bishop(color));
        line.add(new Queen(color));
        line.add(new King(color));
        line.add(new Bishop(color));
        line.add(new Knight(color));
        line.add(new Rook(color));
        board.add(line);
    }

    private void initializePawn(Color color) {
        List<Piece> line = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            line.add(new Pawn(color));
        }
        board.add(line);
    }

    private void initializeBlank() {
        List<Piece> line = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            line.add(new Blank());
        }
        board.add(line);
    }
}
