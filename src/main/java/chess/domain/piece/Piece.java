package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.MovingOrder;
import java.util.Locale;

public abstract class Piece {

    private final Name name;
    private final Color color;

    public Piece(Name name, Color color) {
        this.name = name;
        this.color = color;
    }

    public abstract void checkValidMove(Board board, MovingOrder movingOrder);

    public Color getColor() {
        return color;
    }

    public String getName() {
        if (color.equals(Color.WHITE)) {
            return name.getValue().toLowerCase(Locale.ROOT);
        }
        return name.getValue();
    }

    protected boolean isEmpty() {
        return false;
    }
}
