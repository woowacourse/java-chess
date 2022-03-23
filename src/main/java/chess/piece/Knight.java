package chess.piece;

import chess.Position;
import chess.Team;

public class Knight extends Piece {

    public Knight(Position position, Team team) {
        super(position, team);
    }

    @Override
    public boolean isMovable(Position position) {
        return this.position.isKnightDirection(position);
    }
}
