package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.Direction;
import chess.exception.PieceImpossibleMoveException;

import java.util.Arrays;
import java.util.Objects;

public abstract class Piece {
    protected PieceType pieceType;
    protected Position position;

    public Piece(PieceType pieceType, Position position) {
        this.pieceType = pieceType;
        this.position = position;
    }

    public boolean isMovable(final Board board,  final Piece toPiece) {
        if (pieceType.getPossiblePositions(board, this).contains(toPiece.getPosition())) {
            return true;
        }
        throw new PieceImpossibleMoveException("해당 포지션으로 이동할 수 없습니다.");
    }

    public boolean isKing() {
        return pieceType == PieceType.WHITE_KING | pieceType == PieceType.BLACK_KING;
    }

    public boolean isOtherTeam(Piece nextPiece) {
        return this.pieceType.isOtherTeam(nextPiece.pieceType);
    }

    public boolean isSameTeam(Piece nextPiece) {
        return this.pieceType.isSameTeam(nextPiece.pieceType);
    }

    public boolean isSameTeam(Team team) {
        return this.pieceType.isSameTeam(team);
    }

    public boolean isPawn() {
        return PieceType.isPawn(this);
    }

    public boolean isBlank() {
        return pieceType == PieceType.BLANK;
    }

    public boolean isNextPositionValid(final Direction direction) {
        return Position.isInBoardRange(position.getX() + direction.getColumn(),
                position.getY() + direction.getRow());
    }

    public char getRepresentation() {
        return pieceType.representation();
    }

    public Position getPosition() {
        return position;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public int getRow() {
        return position.getY();
    }

    public double score() {
        return pieceType.score();
    }

    public abstract Piece moveTo(final Position toPosition);

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return pieceType == piece.pieceType &&
                Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, position);
    }

    @Override
    public String toString() {
        return String.valueOf(pieceType.representation());
    }
}