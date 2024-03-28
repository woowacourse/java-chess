package model.piece;

import java.util.HashMap;
import java.util.Map;
import model.Camp;

public class PieceGenerator {

    private static final Map<String, Piece> CASH;

    static {
        CASH = new HashMap<>();
        CASH.put("BISHOPBLACK", new Bishop(Camp.BLACK));
        CASH.put("PAWNBLACK", new BlackPawn());
        CASH.put("KINGBLACK", new King(Camp.BLACK));
        CASH.put("QUEENBLACK", new Queen(Camp.BLACK));
        CASH.put("ROOKBLACK", new Rook(Camp.BLACK));
        CASH.put("KNIGHTBLACK", new Knight(Camp.BLACK));
        CASH.put("BISHOPWHITE", new Bishop(Camp.WHITE));
        CASH.put("PAWNWHITE", new WhitePawn());
        CASH.put("KINGWHITE", new King(Camp.WHITE));
        CASH.put("QUEENWHITE", new Queen(Camp.WHITE));
        CASH.put("ROOKWHITE", new Rook(Camp.WHITE));
        CASH.put("KNIGHTWHITE", new Knight(Camp.WHITE));
    }

    public static Piece getPiece(String input) {
        if (CASH.containsKey(input)) {
            return CASH.get(input);
        }
        throw new IllegalArgumentException("해당 기물은 존재하지 않습니다.");
    }
}
