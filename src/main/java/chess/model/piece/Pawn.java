package chess.model.piece;

import chess.model.position.Movement;

public final class Pawn extends JumpingPiece {
    private static final Piece WHITE_PAWN = new Pawn(Color.WHITE, 1, 2);
    private static final Piece BLACK_PAWN = new Pawn(Color.BLACK, -1, 7);
    private static final int START_JUMP_DISTANCE = 2;
    private static final int COMMON_RANK_DISTANCE = 1;

    private final int validRankDirection;
    private final int startRank;

    private Pawn(Color color, int validRankDirection, int startRank) {
        super(color, Type.PAWN);
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
    public boolean isValid(Movement movement, Piece destination) {
        validateDestinationColor(destination);
        if (!isValidDirection(movement)) {
            return false;
        }
        if (movement.isDiagonal()) {
            return isValidDiagonalMove(movement, destination);
        }
        return isValidVerticalMove(movement);
    }

    private boolean isValidDirection(Movement movement) {
        int movementRankDirection = Integer.signum(movement.getRankGap());
        return movementRankDirection == validRankDirection;
    }

    private boolean isValidDiagonalMove(Movement movement, Piece destination) {
        int rankDistance = movement.getRankDistance();
        return rankDistance == COMMON_RANK_DISTANCE && hasOppositeColorWith(destination);
    }

    private boolean isValidVerticalMove(Movement movement) {
        if (!movement.isSameFile()) {
            return false;
        }
        int rankGap = movement.getRankGap();
        if (rankGap == validRankDirection) {
            return true;
        }
        int rankDistance = movement.getRankDistance();
        return rankDistance == START_JUMP_DISTANCE && movement.isSourceRankMatch(startRank);
    }
}
