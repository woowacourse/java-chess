package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public class Knight extends Piece {

    public static List<String> BLACK_INIT_LOCATIONS = List.of("b8", "g8");
    public static List<String> WHITE_INIT_LOCATIONS = List.of("b1", "g1");

    public Knight(Color color) {
        super(color);
    }
}
