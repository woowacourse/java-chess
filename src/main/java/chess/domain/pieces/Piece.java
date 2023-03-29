package chess.domain.pieces;

import chess.domain.board.Position;
import chess.domain.direction.Route;
import java.util.Objects;

public abstract class Piece {

    private final Team team;
    private final PieceType pieceType;

    public Piece(final Team team, final PieceType pieceType) {
        this.team = team;
        this.pieceType = pieceType;
    }

    public abstract void canMove(final Position start, final Position end, final boolean isAttack);

    public abstract Route generateRoute(final Position source, final Position destination);

    public Team getTeam() {
        return team;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public boolean isEmpty() {
        return team.isEmpty();
    }

    public boolean isWhiteTeam() {
        return team.isWhiteTeam();
    }

    public boolean isBlackTeam() {
        return team.isBlackTeam();
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return team == piece.team && pieceType == piece.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, pieceType);
    }
}
