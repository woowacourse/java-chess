package domain;

import java.util.HashMap;
import java.util.Map;

import domain.piece.Camp;
import domain.piece.Piece;
import domain.piece.nonslider.Empty;
import domain.piece.nonslider.Knight;
import domain.piece.nonslider.Pawn;
import domain.piece.slider.Bishop;
import domain.piece.slider.King;
import domain.piece.slider.Queen;
import domain.piece.slider.Rook;

public class PieceNameConverter {
    private static final Map<Piece, String> piecesAndStrings = new HashMap<>();

    private PieceNameConverter() {
    }

    public static String convert(Piece piece) {
        return piecesAndStrings.get(piece);
    }
    public static Piece convert(String name) {
        for (Map.Entry<Piece, String> pieceAndNames : piecesAndStrings.entrySet()){
            if (pieceAndNames.getValue().equals(name)) {
                return pieceAndNames.getKey();
            }
        }
        throw new IllegalArgumentException("해당 이름을 가진 기물이 없습니다.");
    }

    public static void init() {
        piecesAndStrings.put(Empty.getInstance(), ".");
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
