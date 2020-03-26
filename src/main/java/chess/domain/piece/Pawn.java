package chess.domain.piece;

import chess.domain.BoardState;
import chess.domain.MovingDirection;
import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.exception.MovingDirectionException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Pawn extends Piece {

    protected static final Map<Player, MovingDirection> MOVING_DIRECTION_BY_PLAYER;
    protected static final Map<Player, List<MovingDirection>> ATTACK_DIRECTION_BY_PLAYER;

    private static final String BLACK_PAWN_UNICODE = "\u265F";
    private static final String WHITE_PAWN_UNICODE = "\u2659";

    static {
        MOVING_DIRECTION_BY_PLAYER = new HashMap<>();
        MOVING_DIRECTION_BY_PLAYER.put(Player.WHITE, MovingDirection.NORTH);
        MOVING_DIRECTION_BY_PLAYER.put(Player.BLACK, MovingDirection.SOUTH);

        ATTACK_DIRECTION_BY_PLAYER = new HashMap<>();
        ATTACK_DIRECTION_BY_PLAYER.put(Player.WHITE, Arrays.asList(
                MovingDirection.NORTH_EAST,
                MovingDirection.NORTH_WEST
        ));
        ATTACK_DIRECTION_BY_PLAYER.put(Player.BLACK, Arrays.asList(
                MovingDirection.SOUTH_EAST,
                MovingDirection.SOUTH_WEST
        ));
    }

    protected Pawn(Position position, Player player) {
        super(PieceType.PAWN, position, player);
    }

    @Override
    protected void validateMovingPolicy(Position target, BoardState boardState) {
        MovingDirection movingDirection = MovingDirection.getDirection(position, target);
        validateDirection(movingDirection);
        validateAttack(target, boardState);
        validateMove(target, boardState);
    }

    private void validateDirection(MovingDirection movingDirection) {
        if (!ATTACK_DIRECTION_BY_PLAYER.get(player).contains(movingDirection) &&
                !MOVING_DIRECTION_BY_PLAYER.get(player).equals(movingDirection)) {
            throw new MovingDirectionException();
        }
    }

    protected abstract void validateAttack(Position target, BoardState boardState);

    protected abstract void validateMove(Position target, BoardState boardState);

    @Override
    public String getFigure() {
        if (player == Player.BLACK) {
            return BLACK_PAWN_UNICODE;
        }
        return WHITE_PAWN_UNICODE;
    }
}
