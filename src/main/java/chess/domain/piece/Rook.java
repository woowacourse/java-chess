package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;
import chess.dto.BoardSnapshot;
import chess.strategy.RockStrategy;

public class Rook extends Piece {

    public Rook(Team team) {
        super(Role.ROOK, team, new RockStrategy());
    }

    @Override
    public boolean canMove(Position source, Position target, BoardSnapshot boardSnapshot) {

        return moveStrategy.isMovable(source, target) && canAttack(source, target, boardSnapshot);
    }

    private boolean canAttack(Position source, Position target, BoardSnapshot boardSnapshot) {
        return isValidPieces(boardSnapshot.findByPosition(target)) && hasNotCollision(source, target, boardSnapshot);
    }
}
