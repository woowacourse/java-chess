package chess.domain.board;

import java.util.List;

public class Position {
    private final Vertical v;
    private final Horizontal h;

    public Position(Vertical v, Horizontal h){
        this.h = h;
        this.v = v;
    }
}
