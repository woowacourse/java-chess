package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class PieceFactory {
    
    private PieceFactory() {
    }
    
    
    public static List<Piece> createWhitePawns() {
        return createPawns(Color.WHITE);
    }
    
    private static List<Piece> createPawns(final Color white) {
        return IntStream.range(0, 8).mapToObj(i -> Pawn.create(white)).collect(Collectors.toList());
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
    
    public static Piece createEmptyPiece() {
        return Empty.create();
    }
}
