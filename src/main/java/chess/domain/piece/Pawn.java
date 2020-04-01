package chess.domain.piece;

import chess.domain.position.Position;

public class Pawn extends Piece {
    private static final int START_WHITE_RANK = 2;
    private static final int START_BLACK_RANK = 7;
    private static final int SAME_FILE = 0;
    private static final int DIAGONAL = 1;
    private static final int UP_WHITE_ONE = -1;
    private static final int UP_WHITE_TWO = -2;
    private static final int DOWN_BLACK_ONE = 1;
    private static final int DOWN_BLACK_TWO = 2;

    public Pawn(final PieceType pieceType, final Team team) {
        super(pieceType, team);
    }

    @Override
    public boolean movable(final Position source, final Position target) {
        final int fileGap = source.calculateFileGap(target);
        final int rankGap = source.calculateRankGap(target);

        if (this.team.isWhite()) {
            return whitePawnMovable(source, fileGap, rankGap);
        }
        return blackPawnMovable(source, fileGap, rankGap);
    }

    private boolean whitePawnMovable(final Position source, final int fileGap, final int rankGap) {
        if (source.getRank() == START_WHITE_RANK) {
            return fileGap == SAME_FILE
                    && (rankGap == UP_WHITE_ONE || rankGap == UP_WHITE_TWO);
        }

        final int absFileGap = Math.abs(fileGap);
        return (absFileGap == SAME_FILE || absFileGap == DIAGONAL)
                && rankGap == UP_WHITE_ONE;
    }

    private boolean blackPawnMovable(final Position source, final int fileGap, final int rankGap) {
        if (source.getRank() == START_BLACK_RANK) {
            return fileGap == SAME_FILE
                    && (rankGap == DOWN_BLACK_ONE || rankGap == DOWN_BLACK_TWO);
        }

        final int absFileGap = Math.abs(fileGap);
        return (absFileGap == SAME_FILE || absFileGap == DIAGONAL)
                && rankGap == DOWN_BLACK_ONE;
    }
}
