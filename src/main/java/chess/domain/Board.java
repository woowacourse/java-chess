package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private static final Map<Square, Piece> board = new HashMap<>();

    static {
        initialPieceWithoutPawn(Rank.EIGHT, Color.BLACK);
        initialPawn(Rank.SEVEN, Color.BLACK);
        initialPawn(Rank.TWO, Color.WHITE);
        initialPieceWithoutPawn(Rank.ONE, Color.WHITE);
    }

    private static void initialPieceWithoutPawn(final Rank rank, final Color color) {
        board.put(new Square(File.A, rank), new Rook(color));
        board.put(new Square(File.B, rank), new Knight(color));
        board.put(new Square(File.C, rank), new Bishop(color));
        board.put(new Square(File.D, rank), new Queen(color));
        board.put(new Square(File.E, rank), new King(color));
        board.put(new Square(File.F, rank), new Bishop(color));
        board.put(new Square(File.G, rank), new Knight(color));
        board.put(new Square(File.H, rank), new Rook(color));
    }

    private static void initialPawn(final Rank rank, final Color color) {
        for (final File file : File.values()) {
            board.put(new Square(file, rank), new Pawn(color));
        }
    }

    public static Piece findPieceOf(final Square square) {
        return board.get(square);
    }
}
