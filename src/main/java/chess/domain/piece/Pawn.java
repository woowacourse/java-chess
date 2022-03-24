package chess.domain.piece;

import chess.domain.position.Position;

public class Pawn extends Piece {

    private static final String NAME = "p";
    private static final int WHITE_INIT_RANK = 2;
    private static final int BLACK_INIT_RANK = 7;
    private static final int DEFAULT_FORWARD = 1;
    private static final int INIT_FORWARD = 2;

    public Pawn(final Color color) {
        super(color, NAME);
    }

    @Override
    public void checkPieceMoveRange(final Piece target, final Position from, final Position to) {
        checkMoveDiagonal(target, from, to);
        int fromRank = from.getRankNumber();
        int toRank = to.getRankNumber();
        if (color == Color.WHITE) {
            int distance = distance(fromRank, toRank);
            checkMoveForward(fromRank, distance, WHITE_INIT_RANK);
        }
        if (color == Color.BLACK) {
            int distance = distance(toRank, fromRank);
            checkMoveForward(fromRank, distance, BLACK_INIT_RANK);
        }
    }

    private void checkMoveDiagonal(final Piece target, final Position from, final Position to) {
        if (target == null && from.getFile() != to.getFile()) {
            throw new IllegalArgumentException("폰은 대각선으로 움직일 수 없습니다.");
        }
    }

    private void checkMoveForward(final int start, final int distance, final int initRank) {
        if (start != initRank && distance == DEFAULT_FORWARD) {
            return;
        }
        if (start == initRank && (distance == DEFAULT_FORWARD || distance == INIT_FORWARD)) {
            return;
        }
        throw new IllegalArgumentException("폰은 앞으로 한 칸만 이동할 수 있습니다.");
    }

    private int distance(final int start, final int end) {
        return end - start;
    }
}
