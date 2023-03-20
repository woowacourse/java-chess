package chess.domain.piece;

import static chess.domain.position.Movement.D;
import static chess.domain.position.Movement.DL;
import static chess.domain.position.Movement.DR;
import static chess.domain.position.Movement.U;
import static chess.domain.position.Movement.UL;
import static chess.domain.position.Movement.UR;

import chess.domain.position.Movement;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    private static final Map<Color, Movement> CAN_MOVE_EMPTY_DESTINATION = Map.of(
            Color.BLACK, D,
            Color.WHITE, U
    );
    private static final Map<Color, List<Movement>> CAN_MOVE_ENEMY_DESTINATION = Map.of(
            Color.BLACK, List.of(DR, DL),
            Color.WHITE, List.of(UR, UL)
    );
    private static final Map<Color, Rank> CAN_MOVE_TWO_BLOCK_RANK = Map.of(
            Color.WHITE, Rank.TWO,
            Color.BLACK, Rank.SEVEN
    );

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Movement searchMovement(final Position from, final Position to, final Piece destination) {
        final Movement movement = to.convertMovement(from);
        if (destination.isEmpty() && movement == CAN_MOVE_EMPTY_DESTINATION.get(color)) {
            return searchPathIfDestinationEmpty(from, to, movement);
        }
        if (!destination.isEmpty() && CAN_MOVE_ENEMY_DESTINATION.get(color).contains(movement)) {
            return movement;
        }
        throw new IllegalArgumentException();
    }

    private Movement searchPathIfDestinationEmpty(final Position from, final Position to, final Movement movement) {
        if (from.isEqualRank(CAN_MOVE_TWO_BLOCK_RANK.get(color)) && rankDifference(from, to) == 2) {
            return movement;
        }
        if (rankDifference(from, to) == 1) {
            return movement;
        }
        throw new IllegalArgumentException();
    }

    private int rankDifference(final Position from, final Position to) {
        return Math.abs(to.rankDifference(from));
    }
}
