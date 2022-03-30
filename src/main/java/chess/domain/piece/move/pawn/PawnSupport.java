package chess.domain.piece.move.pawn;

import chess.domain.Color;
import chess.domain.board.LineNumber;
import chess.domain.board.Point;

public class PawnSupport {

    private static final int WHITE_FORWARD = 1;
    private static final int BLACK_FORWARD = -1;

    private final int forward;
    private final int startLine;

    public PawnSupport(Color color) {
        this.forward = determineForwardBy(color);
        this.startLine = determineStartLine(color);
    }

    private int determineStartLine(Color color) {
        if (color == Color.WHITE) {
            return LineNumber.MIN + 1;
        }
        return LineNumber.MAX - 1;
    }

    private int determineForwardBy(Color color) {
        if (color == Color.WHITE) {
            return WHITE_FORWARD;
        }
        return BLACK_FORWARD;
    }

    public int forwarding(int vertical) {
        return vertical * this.forward;
    }

    public boolean isStartLine(Point point) {
        return point.subtractVertical(Point.of(1, startLine)) == 0;
    }
}
