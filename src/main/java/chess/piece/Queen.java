package chess.piece;

import chess.Color;
import chess.Main;
import chess.position.Movement;
import chess.position.Offset;
import chess.position.Position;

public class Queen implements Piece, 퀸비숍룩 {

    private final Color color;

    public Queen(final Color color) {
        this.color = color;
    }

    @Override
    public boolean canMove(final Offset offset, final boolean killFlag) {
        return offset.isDiagonal() || offset.isVerticalOrHorizontal();
    }

    @Override
    public boolean 경로상_장애물_확인(final Position before, final Position after) {
        final Offset offset = Offset.calculate(after, before);
        final Movement direction = Movement.findDirection(offset);

        Position current = before;
        while (true) {
            current = current.plus(direction);
            if (Main.pieceBoard[current.getI()][current.getJ()] != null) {
                return true;
            }

            if (current.equals(after)) {
                break;
            }
        }
        return false;
    }

    @Override
    public Color getColor() {
        return color;
    }


    @Override
    public String toString() {
        return color + "퀸";
    }
}
