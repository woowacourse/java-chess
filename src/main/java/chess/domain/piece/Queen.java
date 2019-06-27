package chess.domain.piece;

import java.util.List;

import chess.domain.*;

public class Queen extends NormalPiece {
    private static final int QUEEN_MAX_DISTANCE = 7;

    private Queen(Player player, Type type, List<MovementInfo> movementInfos, Position currentPosition) {
        super(player, type, movementInfos, currentPosition);
    }

    public static Queen valueOf(Player player, Position currentPosition) {
        List<MovementInfo> movementInfos =
                MovementInfo.makeMovementInfos(Direction.everyDirection(), QUEEN_MAX_DISTANCE);
        return new Queen(player, Type.QUEEN, movementInfos, currentPosition);
    }
}
