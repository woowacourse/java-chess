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
                new Rook(Color.BLACK, new Square(Rank.A, File.EIGHT)),
                new Knight(Color.BLACK, new Square(Rank.B, File.EIGHT)),
                new Bishop(Color.BLACK, new Square(Rank.C, File.EIGHT)),
                new Queen(Color.BLACK, new Square(Rank.D, File.EIGHT)),
                new King(Color.BLACK, new Square(Rank.E, File.EIGHT)),
                new Bishop(Color.BLACK, new Square(Rank.F, File.EIGHT)),
                new Knight(Color.BLACK, new Square(Rank.G, File.EIGHT)),
                new Rook(Color.BLACK, new Square(Rank.H, File.EIGHT))
        );
    }

    public static List<Piece> createBlackPawns() {
        return List.of(
                new Pawn(Color.BLACK, new Square(Rank.A, File.SEVEN)),
                new Pawn(Color.BLACK, new Square(Rank.B, File.SEVEN)),
                new Pawn(Color.BLACK, new Square(Rank.C, File.SEVEN)),
                new Pawn(Color.BLACK, new Square(Rank.D, File.SEVEN)),
                new Pawn(Color.BLACK, new Square(Rank.E, File.SEVEN)),
                new Pawn(Color.BLACK, new Square(Rank.F, File.SEVEN)),
                new Pawn(Color.BLACK, new Square(Rank.G, File.SEVEN)),
                new Pawn(Color.BLACK, new Square(Rank.H, File.SEVEN))
        );
    }

    public static List<Piece> createWhitePawns() {
        return List.of(
                new Pawn(Color.WHITE, new Square(Rank.A, File.TWO)),
                new Pawn(Color.WHITE, new Square(Rank.B, File.TWO)),
                new Pawn(Color.WHITE, new Square(Rank.C, File.TWO)),
                new Pawn(Color.WHITE, new Square(Rank.D, File.TWO)),
                new Pawn(Color.WHITE, new Square(Rank.E, File.TWO)),
                new Pawn(Color.WHITE, new Square(Rank.F, File.TWO)),
                new Pawn(Color.WHITE, new Square(Rank.G, File.TWO)),
                new Pawn(Color.WHITE, new Square(Rank.H, File.TWO))
        );
    }

    public static List<Piece> createEmptyPieces() {
        List<Piece> pieces = new ArrayList<>();
        for (File file : List.of(File.THREE, File.FOUR, File.FIVE, File.SIX)) {
            for (Rank rank : Rank.values()) {
                pieces.add(new EmptyPiece((new Square(rank, file))));
            }
        }
        return pieces;
    }

    public static List<Piece> createWhitePieces() {
        return List.of(
                new Rook(Color.WHITE, new Square(Rank.A, File.ONE)),
                new Knight(Color.WHITE, new Square(Rank.B, File.ONE)),
                new Bishop(Color.WHITE, new Square(Rank.C, File.ONE)),
                new Queen(Color.WHITE, new Square(Rank.D, File.ONE)),
                new King(Color.WHITE, new Square(Rank.E, File.ONE)),
                new Bishop(Color.WHITE, new Square(Rank.F, File.ONE)),
                new Knight(Color.WHITE, new Square(Rank.G, File.ONE)),
                new Rook(Color.WHITE, new Square(Rank.H, File.ONE))
        );
    }
}
