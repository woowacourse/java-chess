package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;
import chess.dto.BoardSnapshot;
import chess.strategy.KnightStrategy;

public class Knight extends Piece {

    public Knight(Team team) {
        super(Role.KNIGHT, team, new KnightStrategy());
    }

    @Override
    public boolean canMove(Position source, Position target, BoardSnapshot boardSnapshot) {
        return moveStrategy.isMovable(source, target) && canAttack(target, boardSnapshot);
    }

    private boolean canAttack(Position target, BoardSnapshot boardSnapshot) {
        return isValidPieces(boardSnapshot.findByPosition(target));
    }
}
