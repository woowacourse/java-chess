package chess.domain.piece;

import chess.domain.Position;

public abstract class Piece {

    private final Color color;
    private final String name;
    private final Position position;

    protected Piece(Color color, String name, Position position) {
        this.color = color;
        this.name = name;
        this.position = position;
    }

    protected Piece(Piece piece, Position position) {
        this(piece.color, piece.name, position);
    }

    public final boolean isEqualColor(Piece piece) {
        return this.color == piece.color;
    }

    public final String convertedName() {
        return color.convertToCase(name);
    }

    public final Position getPosition() {
        return position;
    }
}
