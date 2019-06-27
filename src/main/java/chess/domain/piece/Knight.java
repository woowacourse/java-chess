package chess.domain.piece;

import java.util.List;

import chess.domain.*;

public class Knight extends NormalPiece {
    private static final int KNIGHT_MAX_DISTANCE = 1;

    private Knight(Player player, Type type, List<MovementInfo> movementInfos, Position currentPosition) {
        super(player, type, movementInfos, currentPosition);
    }

    public static Knight valueOf(Player player, Position currentPosition) {
        List<MovementInfo> movementInfos =
                MovementInfo.makeMovementInfos(Direction.knightDirection(), KNIGHT_MAX_DISTANCE);
        return new Knight(player, Type.KNIGHT, movementInfos, currentPosition);
    }
}
