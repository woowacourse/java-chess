package chess.model.piece;

import chess.model.Color;
import chess.model.Square;
import chess.model.File;
import chess.model.Rank;

public abstract class Piece {

    private final Color color;
    private final Square square;

    public Piece(final Color color, final Square square) {
        this.color = color;
        this.square = square;
    }

    public Piece(final Color color, final File file, final Rank rank) {
        this(color, new Square(file, rank));
    }

    public abstract boolean movable(final Piece targetPiece);

    public abstract String getLetter();

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isAt(Square square) {
        return square.equals(square);
    }

    protected Color color() {
        return this.color;
    }
}
