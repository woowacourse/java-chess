package chess.domain.board;

import chess.domain.piece.Color;
import java.util.List;

public enum Direction {
    TOP( 0, 1),
    DOWN( 0, -1),
    LEFT( -1,0 ),
    RIGHT( 1,0 ),
    TOPLEFT( -1,1 ),
    TOPRIGHT( 1,1 ),
    DOWNLEFT(-1 , -1),
    DOWNRIGHT( 1, -1),
    TTR(1 , 2),
    RRT( 2, 1),
    RRD(2 , -1),
    DDR( 1,-2 ),
    DDL(-1 , -2),
    LLD( -2,-1 ),
    LLT( -2,1 ),
    TTL(-1,2);

    private final int x;

    private final int y;
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static List<Direction> pawnDirection(Color color) {
        if (color == Color.WHITE) {
            return List.of(TOP, TOPLEFT, TOPRIGHT);
        }
        return List.of(DOWN, DOWNRIGHT, DOWNLEFT);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
