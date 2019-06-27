package chess.domain.piece;

import java.util.List;

import chess.domain.*;

public class Rook extends NormalPiece {
    private static final int ROOK_MAX_DISTANCE = 7;

    private Rook(Player player, Type type, List<MovementInfo> movementInfos, Position currentPosition) {
        super(player, type, movementInfos, currentPosition);
    }

    public static Rook valueOf(Player player, Position currentPosition) {
        List<MovementInfo> movementInfos =
                MovementInfo.makeMovementInfos(Direction.linearDirection(), ROOK_MAX_DISTANCE);
        return new Rook(player, Type.ROOK, movementInfos, currentPosition);
    }
}
