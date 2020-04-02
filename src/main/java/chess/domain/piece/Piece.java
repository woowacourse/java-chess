package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.Direction;
import chess.exception.PieceImpossibleMoveException;

import java.util.Objects;

public abstract class Piece {
    protected PieceType pieceType;
    protected char representation;
    protected Team team;
    protected Position position;

    public Piece(PieceType pieceType, char representation, Team team, Position position) {
        this.pieceType = pieceType;
        this.representation = representation;
        this.team = team;
        this.position = position;
    }

    public boolean isMovable(final Board board,  final Piece toPiece) {
        if (pieceType.getPossiblePositions(board, this).contains(toPiece.getPosition())) {
            return true;
        }
        throw new PieceImpossibleMoveException("해당 포지션으로 이동할 수 없습니다.");
    }

    public boolean isKing() {
        return this instanceof King;
    }

    public boolean isOtherTeam(final Piece piece) {
        return team.isNotSame(piece.team);
    }

    public boolean isSameTeam(final Piece piece) {
        return team == piece.team;
    }

    public boolean isSameTeam(final Team team) {
        return this.team == team;
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
        return representation;
    }

    public Position getPosition() {
        return position;
    }

    public PieceType getPieceType() {
        return pieceType;
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
        return String.valueOf(representation);
    }
}