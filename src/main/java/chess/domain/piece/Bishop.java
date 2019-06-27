package chess.domain.piece;

import java.util.List;

import chess.domain.*;

public class Bishop extends NormalPiece {
    private static final int BISHOP_MAX_DISTANCE = 7;

    private Bishop(Player player, Type type, List<MovementInfo> movementInfos, Position currentPosition) {
        super(player, type, movementInfos, currentPosition);
    }

    public static Bishop valueOf(Player player, Position currentPosition) {
        List<MovementInfo> movementInfos =
                MovementInfo.makeMovementInfos(Direction.diagonalDirection(), BISHOP_MAX_DISTANCE);
        return new Bishop(player, Type.BISHOP, movementInfos, currentPosition);
    }
}
