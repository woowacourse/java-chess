package chess.domain.piece;

import static chess.domain.direction.Direction.DOWN;
import static chess.domain.direction.Direction.DOWN_LEFT;
import static chess.domain.direction.Direction.DOWN_RIGHT;
import static chess.domain.direction.Direction.UP;
import static chess.domain.direction.Direction.UP_LEFT;
import static chess.domain.direction.Direction.UP_RIGHT;

import chess.domain.direction.Direction;
import chess.domain.piece.movestrategy.MoveStrategy;
import chess.domain.piece.movestrategy.PawnMoveStrategy;
import java.util.List;

public final class Pawn extends Piece {

    Pawn(Symbol symbol, Team team) {
        super(symbol, team);
    }

    @Override
    public List<Direction> direction() {
        if (team.isBlack()) {
            return List.of(DOWN, DOWN_LEFT, DOWN_RIGHT);
        }
        return List.of(UP, UP_LEFT, UP_RIGHT);
    }

    @Override
    public MoveStrategy moveStrategy() {
        return new PawnMoveStrategy();
    }
}
