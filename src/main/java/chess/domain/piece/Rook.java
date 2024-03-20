package chess.domain.piece;

import chess.domain.position.Position;

public class Rook extends Piece {
    public Rook(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination) {
        if (start.isStraightWith(destination)) {
            return true;
        }
        return false;
    }
}
