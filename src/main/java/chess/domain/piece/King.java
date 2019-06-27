package chess.domain.piece;

import java.util.List;

import chess.domain.*;

public class King extends NormalPiece {
    private static final int KING_MAX_DISTANCE = 1;

    private King(Player player, Type type, List<MovementInfo> movementInfos, Position currentPosition) {
        super(player, type, movementInfos, currentPosition);
    }

    public static King valueOf(Player player, Position currentPosition) {
        List<MovementInfo> movementInfos =
                MovementInfo.makeMovementInfos(Direction.everyDirection(), KING_MAX_DISTANCE);
        return new King(player, Type.KING, movementInfos, currentPosition);
    }
}
