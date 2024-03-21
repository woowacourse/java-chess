package chess.domain.chessPiece;

import chess.domain.Position;
import chess.domain.Team;

import java.util.List;

import static chess.domain.chessPiece.Role.BLACK_PAWN;
import static chess.domain.chessPiece.Role.WHITE_PAWN;

public abstract class Piece {
    protected Team team;

    public Piece(Team team) {
        this.team = team;
    }

    public abstract List<Position> getRoute(Position source, Position target);

    protected abstract void validateMovingRule(Position source, Position target);

    public abstract Role getRole();

    public boolean isTeam(Piece piece) {
        return team == piece.team;
    }

    public boolean isPawn() {
        return getRole() == BLACK_PAWN || getRole() == WHITE_PAWN;
    }
}
