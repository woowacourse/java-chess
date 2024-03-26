package chess.model.piece;

import chess.model.position.Movement;
import chess.model.position.RankDirection;

public class Pawn extends Piece {
    private static final Piece WHITE_PAWN = new Pawn(Color.WHITE, RankDirection.UP, 2);
    private static final Piece BLACK_PAWN = new Pawn(Color.BLACK, RankDirection.DOWN, 7);
    private static final int START_JUMP_DISTANCE = 2;
    private static final int COMMON_RANK_DISTANCE = 1;

    private final RankDirection validRankDirection;
    private final int startRank;

    private Pawn(Color color, RankDirection validRankDirection, int startRank) {
        super(color);
        this.validRankDirection = validRankDirection;
        this.startRank = startRank;
    }

    public static Piece from(Color color) {
        if (Color.BLACK == color) {
            return BLACK_PAWN;
        }
        return WHITE_PAWN;
    }

    @Override
    public boolean canMove(Movement movement, Piece target) {
        validateTargetColor(target);
        if (!isValidDirection(movement)) {
            return false;
        }
        if (movement.isDiagonal()) {
            return canMoveDiagonally(movement, target);
        }
        return isValidVerticalMove(movement, target);
    }

    private boolean isValidDirection(Movement movement) {
        return validRankDirection.match(movement);
    }

    private boolean canMoveDiagonally(Movement movement, Piece target) {
        boolean canAttack = hasOppositeColorWith(target);
        return canAttack && movement.getRankDistance() == COMMON_RANK_DISTANCE;
    }

    private boolean isValidVerticalMove(Movement movement, Piece target) {
        if (!movement.isSameFile() || !target.isEmpty()) {
            return false;
        }
        int rankDistance = movement.getRankDistance();
        return rankDistance == COMMON_RANK_DISTANCE ||
                (rankDistance == START_JUMP_DISTANCE && movement.isSourceRankMatch(startRank));
    }
}
