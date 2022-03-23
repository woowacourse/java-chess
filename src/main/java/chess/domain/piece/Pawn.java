package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public class Pawn extends Piece {

    public static List<String> BLACK_INIT_LOCATIONS = List.of("a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7");
    public static List<String> WHITE_INIT_LOCATIONS = List.of("a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2");

    public Pawn(Color color) {
        super(color);
    }
}
