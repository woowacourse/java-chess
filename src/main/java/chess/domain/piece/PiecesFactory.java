package chess.domain.piece;

import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Square;
import java.util.ArrayList;
import java.util.List;

public class PiecesFactory {
    private PiecesFactory() {
    }

    public static List<Piece> createBlackPieces() {
        return List.of(
                new Rook(Color.BLACK, new Square(File.A, Rank.EIGHT)),
                new Knight(Color.BLACK, new Square(File.B, Rank.EIGHT)),
                new Bishop(Color.BLACK, new Square(File.C, Rank.EIGHT)),
                new Queen(Color.BLACK, new Square(File.D, Rank.EIGHT)),
                new King(Color.BLACK, new Square(File.E, Rank.EIGHT)),
                new Bishop(Color.BLACK, new Square(File.F, Rank.EIGHT)),
                new Knight(Color.BLACK, new Square(File.G, Rank.EIGHT)),
                new Rook(Color.BLACK, new Square(File.H, Rank.EIGHT))
        );
    }

    public static List<Piece> createBlackPawns() {
        return List.of(
                new Pawn(Color.BLACK, new Square(File.A, Rank.SEVEN)),
                new Pawn(Color.BLACK, new Square(File.B, Rank.SEVEN)),
                new Pawn(Color.BLACK, new Square(File.C, Rank.SEVEN)),
                new Pawn(Color.BLACK, new Square(File.D, Rank.SEVEN)),
                new Pawn(Color.BLACK, new Square(File.E, Rank.SEVEN)),
                new Pawn(Color.BLACK, new Square(File.F, Rank.SEVEN)),
                new Pawn(Color.BLACK, new Square(File.G, Rank.SEVEN)),
                new Pawn(Color.BLACK, new Square(File.H, Rank.SEVEN))
        );
    }

    public static List<Piece> createWhitePawns() {
        return List.of(
                new Pawn(Color.WHITE, new Square(File.A, Rank.TWO)),
                new Pawn(Color.WHITE, new Square(File.B, Rank.TWO)),
                new Pawn(Color.WHITE, new Square(File.C, Rank.TWO)),
                new Pawn(Color.WHITE, new Square(File.D, Rank.TWO)),
                new Pawn(Color.WHITE, new Square(File.E, Rank.TWO)),
                new Pawn(Color.WHITE, new Square(File.F, Rank.TWO)),
                new Pawn(Color.WHITE, new Square(File.G, Rank.TWO)),
                new Pawn(Color.WHITE, new Square(File.H, Rank.TWO))
        );
    }

    public static List<Piece> createEmptyPieces() {
        List<Piece> pieces = new ArrayList<>();
        for (Rank rank : List.of(Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX)) {
            for (File file : File.values()) {
                pieces.add(new EmptyPiece((new Square(file, rank))));
            }
        }
        return pieces;
    }

    public static List<Piece> createWhitePieces() {
        return List.of(
                new Rook(Color.WHITE, new Square(File.A, Rank.ONE)),
                new Knight(Color.WHITE, new Square(File.B, Rank.ONE)),
                new Bishop(Color.WHITE, new Square(File.C, Rank.ONE)),
                new Queen(Color.WHITE, new Square(File.D, Rank.ONE)),
                new King(Color.WHITE, new Square(File.E, Rank.ONE)),
                new Bishop(Color.WHITE, new Square(File.F, Rank.ONE)),
                new Knight(Color.WHITE, new Square(File.G, Rank.ONE)),
                new Rook(Color.WHITE, new Square(File.H, Rank.ONE))
        );
    }
}
