package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;

import chess.domain.piece.position.Position;

public abstract class Piece implements Chessmen {

    private static final int BLACK_FIRST_RANK = 7;
    private static final int WHITE_FIRST_RANK = 0;

    protected static final String INVALID_MOVABLE_POSITION_EXCEPTION_MESSAGE = "이동할 수 없는 위치입니다.";
    private static final String INVALID_ATTACK_TARGET_EXCEPTION_MESSAGE = "이동하려는 위치에 아군이 있습니다.";

    protected final Color color;
    protected Position position;

    protected Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    @Override
    public void kill(Chessmen targetPiece) {
        if (color == targetPiece.getColor()) {
            throw new IllegalArgumentException(INVALID_ATTACK_TARGET_EXCEPTION_MESSAGE);
        }
        attack(targetPiece.getPosition());
    }

    abstract protected void attack(Position enemyPosition);

    @Override
    public final Position getPosition() {
        return position;
    }

    @Override
    public final Color getColor() {
        return color;
    }

    protected static int firstRankOf(Color color) {
        if (color == BLACK) {
            return BLACK_FIRST_RANK;
        }
        return WHITE_FIRST_RANK;
    }
}
