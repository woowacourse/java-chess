package chess.piece;

import chess.Position;
import chess.Route;
import chess.Team;

public class Queen implements DiagonalMove, PerpendicularMove {

    private final Team team;
    private final Rook rook;
    private final Bishop bishop;

    public Queen(final Team team) {
        this.team = team;
        this.rook = new Rook(team);
        this.bishop = new Bishop(team);
    }

    @Override
    public Route moveRightUp(final Position position) {
        return bishop.moveRightUp(position);
    }

    @Override
    public Route moveRightDown(final Position position) {
        return bishop.moveRightDown(position);
    }

    @Override
    public Route moveLeftUp(final Position position) {
        return bishop.moveLeftUp(position);
    }

    @Override
    public Route moveLeftDown(final Position position) {
        return bishop.moveLeftDown(position);
    }

    @Override
    public Route moveUp(final Position position) {
        return rook.moveUp(position);
    }

    @Override
    public Route moveDown(final Position position) {
        return rook.moveDown(position);
    }

    @Override
    public Route moveRight(final Position position) {
        return rook.moveRight(position);
    }

    @Override
    public Route moveLeft(final Position position) {
        return rook.moveLeft(position);
    }

    @Override
    public PieceMoveType getMoveType() {
        return PieceMoveType.QUEEN;
    }

    @Override
    public Team getTeam() {
        return team;
    }
}
