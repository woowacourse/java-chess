package chess.domain.piece;

import chess.domain.position.Position;

public class Rook extends Piece {
    public Rook(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination) {
        if (start.isStraight(destination)) {
            return true;
        }
        return false;
    }
}
