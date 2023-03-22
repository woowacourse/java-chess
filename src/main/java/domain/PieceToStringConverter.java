package domain;

import java.util.HashMap;
import java.util.Map;

import domain.piece.Camp;
import domain.piece.Piece;
import domain.piece.empty.Empty;
import domain.piece.jumper.Knight;
import domain.piece.pawn.Pawn;
import domain.piece.slider.Bishop;
import domain.piece.slider.King;
import domain.piece.slider.Queen;
import domain.piece.slider.Rook;

public class PieceToStringConverter {
    private static final Map<Piece, String> piecesAndStrings = new HashMap<>();

    public static String convert(Piece piece) {
        init();
        return piecesAndStrings.get(piece);
    }

    private static void init() {
        piecesAndStrings.put(new Empty(), ".");
        piecesAndStrings.put(new King(Camp.WHITE), "k");
        piecesAndStrings.put(new King(Camp.BLACK), "K");
        piecesAndStrings.put(new Queen(Camp.WHITE), "q");
        piecesAndStrings.put(new Queen(Camp.BLACK), "Q");
        piecesAndStrings.put(new Knight(Camp.WHITE), "n");
        piecesAndStrings.put(new Knight(Camp.BLACK), "N");
        piecesAndStrings.put(new Bishop(Camp.WHITE), "b");
        piecesAndStrings.put(new Bishop(Camp.BLACK), "B");
        piecesAndStrings.put(new Rook(Camp.WHITE), "r");
        piecesAndStrings.put(new Rook(Camp.BLACK), "R");
        piecesAndStrings.put(new Pawn(Camp.WHITE), "p");
        piecesAndStrings.put(new Pawn(Camp.BLACK), "P");
    }
}
