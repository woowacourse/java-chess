package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

abstract public class Piece {

    protected Team team;

    protected Piece(final Team team) {
        this.team = team;
    }

    public Direction findDirection(final Square current, final Square destination) {
        int fileDifference = current.getFileDifference(destination);
        int rankDifference = current.getRankDifference(destination);
        return judgeDirection(fileDifference, rankDifference);
    }

    protected abstract Direction judgeDirection(final int fileDifference, final int rankDifference);

    public boolean isAlly(final Team team) {
        return this.team == team;
    }

    public boolean isEnemy(final Piece piece) {
        return !isAlly(piece.team);
    }

    public boolean isPawn() {
        return this instanceof Pawn;
    }

    public boolean isKing() {
        return this instanceof King;
    }

    public Team getColor() {
        return team;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + team +
                '}';
    }
}
