package chess.domain.piece;

import chess.domain.position.Position;

public class Rook extends Piece {
    public Rook(Team team) {
        super(team);
    }

    @Override
    public boolean canMoveTo(Position start, Position destination) {
        if (start.isHorizontalWith(destination) || start.isVerticalWith(destination)) {
            return true;
        }
        return false;
    }
}
