package chess.piece;

import java.util.ArrayList;
import java.util.List;

public class Ranks {

    static List<Rank> board;

    static {
        board = new ArrayList<>();
        board.add(initializePiece(Color.BLACK));
        board.add(initializePiecePawn(Color.BLACK));

        for (int i = 0; i < 4; i++) {
            board.add(initializeBlank());
        }
        board.add(initializePiecePawn(Color.WHITE));
        board.add(initializePiece(Color.WHITE));
    }

    private Ranks() {
    }

    public static List<Rank> create() {
        return new ArrayList<>(board);
    }

    private static Rank initializePiece(Color color) {
        List<Piece> line = new ArrayList<>();
        line.add(new Rook(color));
        line.add(new Knight(color));
        line.add(new Bishop(color));
        line.add(new Queen(color));
        line.add(new King(color));
        line.add(new Bishop(color));
        line.add(new Knight(color));
        line.add(new Rook(color));
        return new Rank(line);
    }

    private static Rank initializePiecePawn(Color color) {
        List<Piece> line = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            line.add(new Pawn(color));
        }
        return new Rank(line);
    }

    private static Rank initializeBlank() {
        List<Piece> line = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            line.add(new Blank());
        }
        return new Rank(line);
    }
}
