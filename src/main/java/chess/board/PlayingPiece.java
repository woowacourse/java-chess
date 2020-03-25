package chess.board;

import chess.piece.Piece;
import chess.position.Position;

import java.util.Objects;

public class PlayingPiece {
    private final Position position;
    private final Piece piece;

    public PlayingPiece(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayingPiece)) return false;
        PlayingPiece that = (PlayingPiece) o;
        return Objects.equals(position, that.position) &&
                Objects.equals(piece, that.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, piece);
    }
}
