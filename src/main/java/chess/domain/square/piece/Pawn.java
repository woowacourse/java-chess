package chess.domain.square.piece;

import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.square.Square;

import java.util.Map;

public class Pawn extends Piece {
    public static final int MOVED_MAX_DIFF = 1;
    public static final int NOT_MOVED_MAX_DIFF = 2;
    private boolean isMoved;

    private Pawn(Color color, boolean isMoved) {
        super(color);
        this.isMoved = isMoved;
    }

    public static Pawn from(Color color) {
        return new Pawn(color, false);
    }

    @Override
    protected boolean isValidPath(Path path) {
        if (color == Color.BLACK) {
            return path.isDown(maxDiff());
        }
        return path.isUp(maxDiff());
    }

    private int maxDiff() {
        if (isMoved) {
            return MOVED_MAX_DIFF;
        }
        return NOT_MOVED_MAX_DIFF;
    }

    @Override
    protected boolean isNotObstructed(Path path, Map<Position, Square> board) {
        return true;
    }

    @Override
    protected void move() {
        isMoved = true;
    }
}
