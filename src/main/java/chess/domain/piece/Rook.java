package chess.domain.piece;

import chess.domain.board.Position;
import chess.strategy.RockStrategy;

public class Rook extends Piece {

    public Rook(Team team) {
        super(Role.ROOK, team, new RockStrategy());
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return moveStrategy.isMovable(source, target);
    }
}
