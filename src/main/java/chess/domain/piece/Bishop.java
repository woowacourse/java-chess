package chess.domain.piece;

import chess.domain.position.Position;

public class Bishop extends Piece {
    public Bishop(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination) {
        return start.isDiagonalWith(destination);
    }
}
