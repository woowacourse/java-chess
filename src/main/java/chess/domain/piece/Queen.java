package chess.domain.piece;

import chess.domain.Color;

public class Queen extends Piece {

    public static String BLACK_INIT_LOCATION = "d8";
    public static String WHITE_INIT_LOCATION = "d1";

    public Queen(Color color) {
        super(color);
    }
}
