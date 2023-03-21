package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;
import chess.dto.BoardSnapshot;
import chess.strategy.EmptyStrategy;

public class Empty extends Piece {

    public static final Empty INSTANCE = new Empty();

    private Empty() {
        super(Role.EMPTY, Team.NONE, new EmptyStrategy());
    }

    @Override
    public boolean canMove(Position source, Position target, BoardSnapshot boardSnapshot) {
        return false;
    }
}
