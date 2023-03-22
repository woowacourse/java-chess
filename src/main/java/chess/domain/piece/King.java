package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;
import chess.strategy.KingStrategy;

public class King extends Piece {

    public King(Team team) {
        super(Role.KING, team, new KingStrategy());
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return moveStrategy.isMovable(source, target);
    }
}
