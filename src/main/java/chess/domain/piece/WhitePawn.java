package chess.domain.piece;

import chess.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhitePawn extends Piece {
    private static int WHITE_INITIAL_Y_COORDINATE = 2;

    private final List<MovementInfo> attackMovementInfos;

    private WhitePawn(Player player, List<MovementInfo> attackMovementInfos, Position position) {
        super(player, Type.PAWN, position);
        this.attackMovementInfos = attackMovementInfos;
    }

    public static WhitePawn valueOf(Player player, Position position) {
        List<MovementInfo> attackMovementInfos = new ArrayList<>(Arrays.asList(
                new MovementInfo(Direction.LEFT_TOP, 1),
                new MovementInfo(Direction.RIGHT_TOP, 1)));
        return new WhitePawn(Player.WHITE, attackMovementInfos, position);
    }

    @Override
    public Path getMovablePath(Position end) {
        if (position.getCoordinateY() == WHITE_INITIAL_Y_COORDINATE) {
            return getValidPath(end, new ArrayList<>(Arrays.asList(new MovementInfo(Direction.TOP, 2))));
        }
        return getValidPath(end, new ArrayList<>(Arrays.asList(new MovementInfo(Direction.TOP, 1))));
    }

    @Override
    public Path getAttackablePath(Position end) {
        return getValidPath(end, attackMovementInfos);
    }
}
