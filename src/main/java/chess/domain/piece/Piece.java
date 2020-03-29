package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected final char representation;
    protected final Team team;
    protected final Position position;

    public Piece(char representation, Team team, Position position) {
        this.representation = representation;
        this.team = team;
        this.position = position;
    }

    public char getRepresentation() {
        return representation;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isMovable(Board board, Position to) {
        if (getPossiblePositions(board).contains(to)) {
            return true;
        }
        throw new UnsupportedOperationException("해당 포지션으로 이동할 수 없습니다.");
    }

    protected boolean isInBoardRange(Position nextPosition) {
        return nextPosition.getX() <= 8 && nextPosition.getX() >= 1 &&
                nextPosition.getY() <= 8 && nextPosition.getY() >= 1;
    }

    public boolean isKing() {
        return representation == 'k' | representation == 'K';
    }

    public boolean isOtherTeam(Piece nextPiece) {
        return this.team.isNotSame(nextPiece.team);
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    @Override
    public String toString() {
        return String.valueOf(representation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return representation == piece.representation &&
                Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(representation, position);
    }

    public abstract List<Position> getPossiblePositions(Board board);

    public abstract Piece movePiecePosition(Position toPosition);
}
