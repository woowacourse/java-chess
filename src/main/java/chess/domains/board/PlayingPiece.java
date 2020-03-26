package chess.domains.board;

import chess.domains.piece.Blank;
import chess.domains.piece.King;
import chess.domains.piece.Knight;
import chess.domains.piece.Piece;
import chess.domains.position.Position;

import java.util.HashSet;
import java.util.List;
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
        return this.position.equals(position);
    }

    public void validMove(Position targetPosition) {
        if (!piece.isValidMove(this.position, targetPosition)) {
            throw new IllegalArgumentException("말의 규칙에 어긋나는 위치로 이동할 수 없습니다.");
        }
    }

    public List<Position> findRoute(Position target) {
        validMove(target);
        return this.position.findRoute(target);
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

    public boolean isKnight() {
        return this.piece instanceof Knight;
    }

    public boolean isKing() {
        return this.piece instanceof King;
    }
}
