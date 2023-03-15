package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public class PiecesFactory {
    public static Pieces create(Color color) {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            pieces.add(new King(color));
            pieces.add(new Pawn(color));
        }
        return new Pieces(pieces);
    }

}
