package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {
    private final Team team;

    public Piece(Team team) {
        this.team = team;
    }

    public abstract boolean canMove(Position start, Position destination, Piece pieceAtDestination);

    public boolean isBlackTeam() {
        return team == Team.BLACK;
    }

    public boolean isOtherTeam(Piece otherPiece) {
        return this.team != otherPiece.team;
    }

    public boolean isOtherTeam(Team team) {
        return this.team != team;
    }

    public boolean isEmpty() {
        return false;
    }
}
