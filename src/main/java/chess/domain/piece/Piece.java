package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import java.util.Locale;

public abstract class Piece {

    protected final Name name;
    protected final Color color;

    public Piece(Name name, Color color) {
        this.name = name;
        this.color = color;
    }

    public abstract void canMove(Board board, Position from, Position to);

    public Color getColor() {
        return color;
    }

    public String getName() {
        if (color.equals(Color.WHITE)) {
            return name.getValue().toLowerCase(Locale.ROOT);
        }
        return name.getValue();
    }

    public boolean isEmpty() {
        return false;
    }
}
