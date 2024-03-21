package chess.domain.piece;

import java.util.List;
import java.util.Objects;
import chess.domain.board.Coordinate;

abstract class AbstractPiece implements Piece {

    private final PieceType type;
    private final Team team;

    public AbstractPiece(PieceType type, Team team) {
        this.type = type;
        this.team = team;
    }

    @Override
    public abstract List<Coordinate> findMovablePath(Coordinate start, Coordinate destination);

    @Override
    public PieceType getType() {
        return type;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPiece piece = (AbstractPiece) o;
        return type == piece.type && team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, team);
    }
}
