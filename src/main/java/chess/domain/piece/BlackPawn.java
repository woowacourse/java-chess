package chess.domain.piece;

import chess.domain.*;

import java.util.List;

public class BlackPawn extends Piece {
    private static final int BLACK_INITIAL_Y_COORDINATE = 7;
    private static final int INITIAL_PAWN_MOVABLE_DISTANCE = 2;
    private static final int PAWN_MOVABLE_DISTANCE = 1;
    private static final int PAWN_ATTACKABLE_DISTANCE = 1;

    private BlackPawn(Player player, Position position) {
        super(player, Type.PAWN, position);
    }

    public static BlackPawn valueOf(Player player, Position position) {
        return new BlackPawn(Player.BLACK, position);
    }

    @Override
    public Path getMovablePath(Position end) {
        List<Direction> directions = Direction.blackPawnMovalbeDirection();
        if (position.getCoordinateY() == BLACK_INITIAL_Y_COORDINATE) {
            return getValidPath(end, MovementInfo.makeMovementInfos(directions, INITIAL_PAWN_MOVABLE_DISTANCE));
        }
        return getValidPath(end, MovementInfo.makeMovementInfos(directions, PAWN_MOVABLE_DISTANCE));
    }

    @Override
    public Path getAttackablePath(Position end) {
        return getValidPath(end,
                MovementInfo.makeMovementInfos(Direction.blackPawnAttackableDirection(), PAWN_ATTACKABLE_DISTANCE));
    }
}
