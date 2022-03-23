package chess.piece;

import chess.Position;
import chess.Team;

public class Rook extends Piece {
    public Rook(Position position, Team team) {
        super(position, team);
    }

    @Override
    public boolean isMovable(Position position) {
        return isCorrectDirection(position);
    }

    private boolean isCorrectDirection(Position position) {
        return this.position.isVertical(position) || this.position.isHorizontal(position);
    }
}
