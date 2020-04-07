package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.Direction;
import chess.exception.PieceImpossibleMoveException;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected PieceType pieceType;
    protected char representation;
    protected Turn turn;
    protected Position position;

    public Piece(final PieceType pieceType, final char representation, final Turn turn, final Position position) {
        this.pieceType = pieceType;
        this.representation = representation;
        this.turn = turn;
        this.position = position;
    }

    public List<Position> possiblePositions(final Board board) {
        return pieceType.possiblePositions(board, this);
    }

    public boolean isMovable(final Board board,  final Piece toPiece) {
        if (pieceType.possiblePositions(board, this).contains(toPiece.getPosition())) {
            return true;
        }
        throw new PieceImpossibleMoveException("해당 포지션으로 이동할 수 없습니다.");
    }

    public boolean isOtherTeam(final Piece piece) {
        return turn.isNotSame(piece.turn);
    }

    public boolean isSameTeam(final Piece piece) {
        return turn == piece.turn;
    }

    public boolean isSameTeam(final Turn turn) {
        return this.turn == turn;
    }

    public boolean isKing() {
        return this instanceof King;
    }

    public boolean isPawn() {
        return this instanceof Pawn;
    }

    public boolean isBlank() {
        return this instanceof Blank;
    }

    public boolean isNextPositionValid(final Direction direction) {
        return Position.isInBoardRange(position.getX() + direction.getColumn(),
                position.getY() + direction.getRow());
    }

    public Position getPosition() {
        return position;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public String getName() {
        return representation + "_" + turn.toString().toLowerCase();
    }

    public char getRepresentation() {
        return representation;
    }

    public List<Direction> directions() {
        return pieceType.directions();
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
        return position.toString();
    }
}