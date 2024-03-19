package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.File;
import domain.piece.info.Position;
import domain.piece.info.Rank;
import java.util.ArrayList;
import java.util.List;

public class Pieces {
    private final List<Piece> pieces;

    public Pieces(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Pieces init() {
        return new Pieces(makePieces());
    }

    private static List<Piece> makePieces() {
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(makePawns());
        pieces.addAll(makeRooks());
        pieces.addAll(makeKnights());
        pieces.addAll(makeBishops());
        pieces.addAll(makeQueens());
        pieces.addAll(makeKings());
        return pieces;
    }

    private static List<Piece> makePawns() {
        List<Piece> pawns = new ArrayList<>();
        for (File file : File.values()) {
            pawns.add(new Pawn(Color.BLACK, new Position(file, Rank.SEVEN)));
        }
        for (File file : File.values()) {
            pawns.add(new Pawn(Color.WHITE, new Position(file, Rank.TWO)));
        }
        return pawns;
    }

    private static List<Piece> makeRooks() {
        List<Piece> rooks = new ArrayList<>();
        rooks.add(new Rook(Color.BLACK, new Position(File.A, Rank.EIGHT)));
        rooks.add(new Rook(Color.BLACK, new Position(File.H, Rank.EIGHT)));
        rooks.add(new Rook(Color.WHITE, new Position(File.A, Rank.ONE)));
        rooks.add(new Rook(Color.WHITE, new Position(File.H, Rank.ONE)));
        return rooks;
    }

    private static List<Piece> makeKnights() {
        List<Piece> knights = new ArrayList<>();
        knights.add(new Knight(Color.BLACK, new Position(File.B, Rank.EIGHT)));
        knights.add(new Knight(Color.BLACK, new Position(File.G, Rank.EIGHT)));
        knights.add(new Knight(Color.WHITE, new Position(File.B, Rank.ONE)));
        knights.add(new Knight(Color.WHITE, new Position(File.G, Rank.ONE)));
        return knights;
    }

    private static List<Piece> makeBishops() {
        List<Piece> bishops = new ArrayList<>();
        bishops.add(new Bishop(Color.BLACK, new Position(File.C, Rank.EIGHT)));
        bishops.add(new Bishop(Color.BLACK, new Position(File.F, Rank.EIGHT)));
        bishops.add(new Bishop(Color.WHITE, new Position(File.C, Rank.ONE)));
        bishops.add(new Bishop(Color.WHITE, new Position(File.F, Rank.ONE)));
        return bishops;
    }

    private static List<Piece> makeQueens() {
        List<Piece> queens = new ArrayList<>();
        queens.add(new Queen(Color.BLACK, new Position(File.D, Rank.EIGHT)));
        queens.add(new Queen(Color.WHITE, new Position(File.D, Rank.ONE)));
        return queens;
    }

    private static List<Piece> makeKings() {
        List<Piece> kings = new ArrayList<>();
        kings.add(new King(Color.BLACK, new Position(File.E, Rank.EIGHT)));
        kings.add(new King(Color.WHITE, new Position(File.E, Rank.ONE)));
        return kings;
    }

    public List<Piece> pieces() {
        return pieces;
    }
}
