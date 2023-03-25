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
}
