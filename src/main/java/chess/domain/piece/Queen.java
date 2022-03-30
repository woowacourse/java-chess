package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.piece.movestrategy.MoveStrategy;
import chess.domain.piece.movestrategy.RepeatableMoveStrategy;

public class Queen extends Piece {
    public Queen(Symbol symbol, Team team) {
        super(symbol, team);
    }

    @Override
    public boolean hasNotDirection(Direction direction) {
        return !Direction.queenDirection()
                .contains(direction);
    }

    @Override
    public MoveStrategy moveStrategy() {
        return new RepeatableMoveStrategy();
    }
}
