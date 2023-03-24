package chess.domain.board;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    public static Board generate() {
        return new Board(initBoard());
    }

    private static Map<Square, Piece> initBoard() {
        final Map<Square, Piece> board = new HashMap<>();
        initialPieceWithoutPawn(board, Rank.EIGHT, Color.BLACK);
        initialPawn(board, Rank.SEVEN, Color.BLACK);
        initialPawn(board, Rank.TWO, Color.WHITE);
        initialPieceWithoutPawn(board, Rank.ONE, Color.WHITE);
        return board;
    }

    private static void initialPieceWithoutPawn(final Map<Square, Piece> board, final Rank rank, final Color color) {
        board.put(new Square(File.A, rank), new Rook(color));
        board.put(new Square(File.B, rank), new Knight(color));
        board.put(new Square(File.C, rank), new Bishop(color));
        board.put(new Square(File.D, rank), new Queen(color));
        board.put(new Square(File.E, rank), new King(color));
        board.put(new Square(File.F, rank), new Bishop(color));
        board.put(new Square(File.G, rank), new Knight(color));
        board.put(new Square(File.H, rank), new Rook(color));
    }

    private static void initialPawn(final Map<Square, Piece> board, final Rank rank, final Color color) {
        for (final File file : File.values()) {
            board.put(new Square(file, rank), new Pawn(color));
        }
    }
}
