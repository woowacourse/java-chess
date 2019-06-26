package chess.domain.piece;

import chess.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackPawn extends Piece {
    private static int BLACK_INITIAL_Y_COORDINATE = 7;

    private final List<MovementInfo> attackMovementInfos;

    private BlackPawn(Player player, List<MovementInfo> attackMovementInfos, Position position) {
        super(player, Type.PAWN, position);
        this.attackMovementInfos = attackMovementInfos;
    }

    public static BlackPawn valueOf(Player player, Position position) {
        List<MovementInfo> attackMovementInfos = new ArrayList<>(Arrays.asList(
                new MovementInfo(Direction.LEFT_BOTTOM, 1),
                new MovementInfo(Direction.RIGHT_BOTTOM, 1)));
        return new BlackPawn(Player.BLACK, attackMovementInfos, position);
    }

    @Override
    public Path getMovablePath(Position end) {
        if (position.getCoordinateY() == BLACK_INITIAL_Y_COORDINATE) {
            return getValidPath(end, new ArrayList<>(Arrays.asList(new MovementInfo(Direction.BOTTOM, 2))));
        }
        return getValidPath(end, new ArrayList<>(Arrays.asList(new MovementInfo(Direction.BOTTOM, 1))));
    }

    @Override
    public Path getAttackablePath(Position end) {
        return getValidPath(end, attackMovementInfos);
    }
}
