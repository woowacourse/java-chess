package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Tile {
    private final Position position;
    private final Piece piece;

    public Tile(final Position position, final Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public String position() {
        return this.position.toString();
    }

    public String pieceImageUrl() {
        return this.piece.toSymbol()
                + "_"
                + this.piece.teamName().toLowerCase();
    }
}
