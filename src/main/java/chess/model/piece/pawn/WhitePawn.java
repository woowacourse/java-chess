package chess.model.piece.pawn;

import chess.model.Color;
import chess.model.Rank;
import chess.model.strategy.PawnMovableStrategy;
import chess.model.strategy.move.Direction;
import java.util.List;

public class WhitePawn extends Pawn {

    private static final List<Direction> WHITE_MOVE_DIRECTION = List.of(Direction.NORTH);
    private static final List<Direction> WHITE_ATTACK_DIRECTION = List.of(Direction.NORTHEAST, Direction.NORTHWEST);
    private static final Rank WHITE_START_RANK = Rank.TWO;

    public WhitePawn() {
        super(Color.WHITE, new PawnMovableStrategy(WHITE_MOVE_DIRECTION, WHITE_ATTACK_DIRECTION, WHITE_START_RANK));
    }
}
