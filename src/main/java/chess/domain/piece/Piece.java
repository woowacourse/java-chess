package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.strategy.MoveStrategy;

import java.util.Objects;

public abstract class Piece {
    protected final MoveStrategy moveStrategy;
    protected final char representation;
    protected final Team team;
    protected final Position position;

    public Piece(MoveStrategy moveStrategy, char representation, Team team, Position position) {
        this.moveStrategy = moveStrategy;
        this.representation = representation;
        this.team = team;
        this.position = position;
    }

    public Board move(Board board, Piece toPiece, Team currentTurn) {
        return moveStrategy.movePieceWithTurnValidation(board, this, toPiece, currentTurn);
    }

    public char getRepresentation() {
        return representation;
    }

    public Position getPosition() {
        return position;
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

    public boolean isNotSameTeam(Team currentTurn) {
        return this.team != currentTurn;
    }

    public boolean isPawn() {
        return this.getClass().equals(WhitePawn.class) | this.getClass().equals(BlackPawn.class);
    }

    public boolean isBlank() {
        return this.getClass().equals(Blank.class);
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

    public abstract Piece movedPiece(Position toPosition);

    public abstract double score();
}
