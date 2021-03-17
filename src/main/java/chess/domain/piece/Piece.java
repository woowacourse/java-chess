package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;

import java.util.List;

public abstract class Piece {
    private final String team;

    public Piece(final String team) {
        this.team = team;
    }

    public abstract boolean isMovable(final Position current, final Position destination, final ChessBoard chessBoard);

    abstract boolean checkPositionRule(final Position current, final Position destination);

    public final boolean checkDiagonalRule(final Position current, final Position destination) {
        final int xDiff = Math.abs(current.getX() - destination.getX());
        final int yDiff = Math.abs(current.getY() - destination.getY());
        if (xDiff == yDiff) {
            return true;
        }
        return false;
    }

    public final boolean checkStraightRule(final Position current, final Position destination) {
        if (current.getX() == destination.getX() && current.getY() != destination.getY()) {
            return true;
        }
        if (current.getY() == destination.getY() && current.getX() != destination.getX()) {
            return true;
        }
        return false;
    }

    public final boolean checkEmptyPath(final List<Position> path, final ChessBoard chessBoard) {
        for (Position position : path) {
            if (chessBoard.havePiece(position)) {
                return false;
            }
        }
        return true;
    }

    public final String getTeam() {
        return team;
    }
}
