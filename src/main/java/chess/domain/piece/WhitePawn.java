package chess.domain.piece;

import chess.domain.*;

import java.util.List;

public class WhitePawn extends Piece {
    private static final int WHITE_INITIAL_Y_COORDINATE = 2;
    private static final int INITIAL_PAWN_MOVABLE_DISTANCE = 2;
    private static final int PAWN_MOVABLE_DISTANCE = 1;
    private static final int PAWN_ATTACKABLE_DISTANCE = 1;

    private WhitePawn(Player player, Position position) {
        super(player, Type.PAWN, position);
    }

    public static WhitePawn valueOf(Player player, Position position) {
        return new WhitePawn(Player.WHITE, position);
    }

    @Override
    public Path getMovablePath(Position end) {
        List<Direction> directions = Direction.whitePawnMovalbeDirection();
        if (position.getCoordinateY() == WHITE_INITIAL_Y_COORDINATE) {
            return getValidPath(end, MovementInfo.makeMovementInfos(directions, INITIAL_PAWN_MOVABLE_DISTANCE));
        }
        return getValidPath(end, MovementInfo.makeMovementInfos(directions, PAWN_MOVABLE_DISTANCE));
    }

    @Override
    public Path getAttackablePath(Position end) {
        return getValidPath(end,
                MovementInfo.makeMovementInfos(Direction.whitePawnAttackableDirection(), PAWN_ATTACKABLE_DISTANCE));
    }
}
