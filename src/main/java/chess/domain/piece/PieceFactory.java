package chess.domain.piece;

import chess.domain.Color;
import java.util.ArrayList;
import java.util.List;

public class PieceFactory {

    public static List<Piece> createWhitePawns() {
        return createPawns(Color.WHITE);
    }
    
    private static List<Piece> createPawns(final Color white) {
        List<Piece> pawns = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            pawns.add(Pawn.create(white));
        }
        return pawns;
    }
    
    public static List<Piece> createBlackPawns() {
        return createPawns(Color.BLACK);
    }
    
    public static List<Piece> createWhiteGenerals() {
        return createGenerals(Color.WHITE);
    }
    
    private static List<Piece> createGenerals(final Color color) {
        List<Piece> generals = new ArrayList<>();
        generals.add(Rook.create(color));
        generals.add(Knight.create(color));
        generals.add(Bishop.create(color));
        generals.add(Queen.create(color));
        generals.add(King.create(color));
        generals.add(Bishop.create(color));
        generals.add(Knight.create(color));
        generals.add(Rook.create(color));
        return generals;
    }
    
    public static List<Piece> createBlackGenerals() {
        return createGenerals(Color.BLACK);
    }
}
