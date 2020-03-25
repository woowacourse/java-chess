package chess.domains.board;

import chess.domains.piece.Piece;
import chess.domains.position.Position;

import java.util.Objects;

public class PlayingPiece implements Comparable<PlayingPiece> {
    private final Position position;
    private final Piece piece;

    public PlayingPiece(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public String showPieceName() {
        return piece.getName();
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

    @Override
    public int compareTo(PlayingPiece o) {
        return this.position.compareTo(o.position);
    }
}
