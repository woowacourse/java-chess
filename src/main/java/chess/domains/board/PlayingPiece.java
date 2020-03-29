package chess.domains.board;

import chess.domains.piece.*;
import chess.domains.position.Direction;
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

    public void validMove(PlayingPiece targetPiece) {
        if (this.isMine(targetPiece)) {
            throw new IllegalStateException("자신의 말 위치로 이동할 수 없습니다.");
        }

        if (!piece.isValidMove(this.position, targetPiece.position)) {
            throw new IllegalArgumentException("말의 규칙에 어긋나는 위치로 이동할 수 없습니다.");
        }

        if (isPawn()) {
            validPawnMove(targetPiece);
        }
    }

    private void validPawnMove(PlayingPiece targetPiece) {
        Direction direction = Direction.findDirection(this.position, targetPiece.position);

        if (direction.isDiagonal() && targetPiece.isBlank()) {
            throw new IllegalArgumentException("폰은 상대말을 잡는 경우 이 외에 대각선으로 이동할 수 없습니다.");
        }

        if (direction.isVertical() && !targetPiece.isBlank()) {
            throw new IllegalArgumentException("폰은 앞에 있는 상대를 잡을 수 없습니다.");
        }
    }

    public List<Position> findRoute(PlayingPiece targetPiece) {
        return this.position.findRoute(targetPiece.position);
    }

    public boolean isBlank() {
        return this.piece.equals(new Blank());
    }

    public boolean isMine(PlayingPiece targetPiece) {
        return this.piece.isMine(targetPiece.piece);
    }

    public boolean isMine(PieceColor pieceColor) {
        return this.piece.isMine(pieceColor);
    }

    public void checkSameColorWith(PieceColor pieceColor) {
        if (!this.piece.isMine(pieceColor)) {
            throw new IllegalArgumentException("상대방의 말을 움직일 수 없습니다.");
        }
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

    public boolean isPawn() {
        return this.piece instanceof Pawn;
    }

    public double score() {
        return piece.getScore();
    }

    public boolean isColumn(char column) {
        return this.position.isColumn(column);
    }

    @Override
    public int compareTo(PlayingPiece o) {
        return this.position.compareTo(o.position);
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
