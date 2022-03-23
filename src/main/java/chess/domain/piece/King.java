package chess.domain.piece;

import chess.domain.Color;

public class King extends Piece {

    public static String BLACK_INIT_LOCATION = "e8";
    public static String WHITE_INIT_LOCATION = "e1";

    public King(Color color) {
        super(color);
    }
}
