package chess.domain.piece;

import chess.domain.board.Position;
import chess.strategy.QueenStrategy;

public class Queen extends Piece {

    public Queen(Team team) {
        super(Role.QUEEN, team, new QueenStrategy());
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return moveStrategy.isMovable(source, target);
    }
}
