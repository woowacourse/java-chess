package chess.domains.board;

import chess.domains.piece.Blank;
import chess.domains.piece.Piece;
import chess.domains.position.Position;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    public boolean has(Position position) {
        return this.position == (position);
    }

    public boolean canMove(Position targetPosition) {
        return piece.canMove(this.position, targetPosition);
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

    public boolean isBlank() {
        return this.piece.equals(new Blank());
    }

    public boolean isMine(PlayingPiece targetPiece) {
        return this.piece.isMine(targetPiece.piece);
    }

    public Piece getPiece() {
        return this.piece;
    }

    public Set<PlayingPiece> moveTo(PlayingPiece targetPiece) {
        Set<PlayingPiece> changedPieces = new HashSet<>();

        changedPieces.add(new PlayingPiece(targetPiece.position, this.piece));
        changedPieces.add(new PlayingPiece(this.position, new Blank()));

        return changedPieces;
    }
}
