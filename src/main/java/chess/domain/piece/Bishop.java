package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public class Bishop extends Piece {

    public static List<String> BLACK_INIT_LOCATIONS = List.of("c8", "f8");
    public static List<String> WHITE_INIT_LOCATIONS = List.of("c1", "f1");

    public Bishop(Color color) {
        super(color, PieceName.BISHOP);
    }
}
