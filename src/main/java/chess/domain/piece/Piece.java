package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;

public abstract class Piece {

    private Position position;
    private TeamColor teamColor;

    public Piece(TeamColor teamColor, Position position) {
        this.teamColor = teamColor;
        this.position = position;
    }

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }
}
