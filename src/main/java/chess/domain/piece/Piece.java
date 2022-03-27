package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Rank;

public abstract class Piece {
    private static final String ERROR_MESSAGE_POSITION_SAME_TEAM = "[ERROR] 아군의 말이 있는 곳으로는 이동할 수 없습니다.";

    protected final Color color;
    protected double score;

    Piece(Color color) {
        this.color = color;
        this.score = 0;
    }

    public static Piece from(File file, Rank rank) {
        return PieceGenerator.generatePiece(file, rank);
    }

    abstract public String getEmoji();

    abstract public boolean canMove(Direction direction, Piece otherPiece);

    void checkSameTeam(Piece otherPiece) {
        if (isSameColor(otherPiece.color)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_SAME_TEAM);
        }
    }

    public boolean isSameColor(Color color) {
        return color == this.color;
    }

    public double addScore(double sum) {
        return sum + score;
    }

    public boolean isNone() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }
}
