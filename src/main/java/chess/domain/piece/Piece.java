package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.attribute.Team;
import chess.domain.piece.strategy.MoveStrategy;
import java.util.List;
import java.util.Locale;

public abstract class Piece {

    protected final Name name;
    protected final Team team;
    protected final MoveStrategy moveStrategy;

    public Piece(Name name, Team team, MoveStrategy moveStrategy) {
        this.name = name;
        this.team = team;
        this.moveStrategy = moveStrategy;
    }

    public abstract boolean canMove(Piece targetPiece, Position from, Position to);

    public Team getColor() {
        return team;
    }

    public String getName() {
        if (team.equals(Team.WHITE)) {
            return name.getValue().toLowerCase(Locale.ROOT);
        }
        return name.getValue();
    }

    public boolean isKing() {
        return false;
    }

    public boolean isSameTeam(Piece targetPiece) {
        return this.team == targetPiece.team;
    }

    public boolean isOppositeColor(Team team) {
        return (team == Team.WHITE && this.team == Team.BLACK) ||
                (team == Team.BLACK && this.team == Team.WHITE);
    }

    public List<Position> getRoute(Position from, Position to) {
        return moveStrategy.getRoute(from, to);
    }

    public boolean isPiece() {
        return true;
    }
}
