package chess.model.piece.pawn;

import chess.model.Color;
import chess.model.Rank;
import chess.model.strategy.PawnMovableStrategy;
import chess.model.strategy.move.Direction;
import java.util.List;

public class BlackPawn extends Pawn {

    private static final List<Direction> BLACK_MOVE_DIRECTION = List.of(Direction.SOUTH);
    private static final List<Direction> BLACK_ATTACK_DIRECTION = List.of(Direction.SOUTHEAST, Direction.SOUTHWEST);
    private static final Rank BLACK_START_RANK = Rank.SEVEN;

    public BlackPawn() {
        super(Color.BLACK, new PawnMovableStrategy(BLACK_MOVE_DIRECTION, BLACK_ATTACK_DIRECTION, BLACK_START_RANK));
    }
}
