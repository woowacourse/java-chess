package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public class Rook extends Piece {

    public static List<String> BLACK_INIT_LOCATIONS = List.of("a8", "h8");
    public static List<String> WHITE_INIT_LOCATIONS = List.of("a1", "h1");

    public Rook(Color color) {
        super(color, PieceName.ROOK);
    }
}
