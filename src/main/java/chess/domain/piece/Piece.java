package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;

import chess.domain.piece.position.Position;

public abstract class Piece {

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

    abstract public void move(Position position);

    public void kill(Piece targetPiece) {
        if (color == targetPiece.color) {
            throw new IllegalArgumentException(INVALID_ATTACK_TARGET_EXCEPTION_MESSAGE);
        }
        attack(targetPiece.position);
    }

    abstract protected void attack(Position enemyPosition);

    abstract public boolean isPawn();

    abstract public boolean isKing();

    abstract public double score();

    abstract public String display();

    public final Position getPosition() {
        return position;
    }

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
