package chess.domain.chesspiece;

import java.util.Collections;

public class Blank extends ChessPiece {
    public Blank(Team team) {
        super(".", team, 0, Collections.EMPTY_LIST);
    }

}
