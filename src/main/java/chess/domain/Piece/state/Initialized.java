package chess.domain.Piece.state;

import chess.domain.Piece.Piece;
import chess.domain.Piece.team.Team;
import chess.domain.Position;

public abstract class Initialized implements Piece {
    protected final Position position;
    protected final Team team;
    protected Initialized(Position position, Team team) {
        this.position = position;
        this.team = team;
    }

    protected abstract boolean canMove(Position to, Piece exPiece);
}
