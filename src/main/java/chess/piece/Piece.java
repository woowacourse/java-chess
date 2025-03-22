package chess.piece;

import chess.Position;
import chess.Route;
import chess.Team;

public class Piece {

    private final DiagonalMove diagonalMove;
    private final PerpendicularMove perpendicularMove;
    private final LMove lMove;

    public Piece(final DiagonalMove diagonalMove, final PerpendicularMove perpendicularMove, final LMove lMove) {
        this.diagonalMove = diagonalMove;
        this.perpendicularMove = perpendicularMove;
        this.lMove = lMove;
    }

    public boolean canMoveDiagonal() {
        return diagonalMove != null;
    }

    public boolean canMovePerpendicular() {
        return perpendicularMove != null;
    }

    public boolean canLMove() {
        return lMove != null;
    }

    public Route moveRightUp(final Position position) {
        return diagonalMove.moveRightUp(position);
    }

    public Route moveRightDown(final Position position) {
        return diagonalMove.moveRightDown(position);
    }

    public Route moveLeftUp(final Position position) {
        return diagonalMove.moveLeftUp(position);
    }

    public Route moveLeftDown(final Position position) {
        return diagonalMove.moveLeftDown(position);
    }

    public Route moveUp(final Position position) {
        return perpendicularMove.moveUp(position);
    }

    public Route moveDown(final Position position) {
        return perpendicularMove.moveDown(position);
    }

    public Route moveRight(final Position position) {
        return perpendicularMove.moveRight(position);
    }

    public Route moveLeft(final Position position) {
        return perpendicularMove.moveLeft(position);
    }

    public Route moveUpRightUp(final Position position) {
        return lMove.moveUpRightUp(position);
    }

    public Route moveUpLeftUp(final Position position) {
        return lMove.moveUpLeftUp(position);
    }

    public Route moveRightUpRight(final Position position) {
        return lMove.moveRightUpRight(position);
    }

    public Route moveRightDownRight(final Position position) {
        return lMove.moveRightDownRight(position);
    }

    public Route moveDownRightDown(final Position position) {
        return lMove.moveDownRightDown(position);
    }

    public Route moveDownLeftDown(final Position position) {
        return lMove.moveDownLeftDown(position);
    }

    public Route moveLeftUpLeft(final Position position) {
        return lMove.moveLeftUpLeft(position);
    }

    public Route moveLeftDownLeft(final Position position) {
        return lMove.moveLeftDownLeft(position);
    }

    public PieceMoveType getMoveType() {
        if (diagonalMove != null) {
            return diagonalMove.getMoveType();
        }
        if (perpendicularMove != null) {
            return perpendicularMove.getMoveType();
        }
        return lMove.getMoveType();
    }

    public Team getTeam() {
        if (diagonalMove != null) {
            return diagonalMove.getTeam();
        }
        if (perpendicularMove != null) {
            return perpendicularMove.getTeam();
        }
        return lMove.getTeam();
    }
}
