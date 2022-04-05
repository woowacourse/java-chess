package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Rank;

public abstract class Piece {
    private static final String ERROR_MESSAGE_POSITION_SAME_TEAM = "[ERROR] 사격 중지!! 아군이다!! ><\n";

    protected final Color color;
    protected double score;
    protected final String type;

    protected Piece(Color color, double score, String type) {
        this.color = color;
        this.score = score;
        this.type = type;
    }

    public static Piece from(File file, Rank rank) {
        return PieceGenerator.generatePiece(file, rank);
    }

    abstract public String getEmoji();

    abstract public boolean canMove(Direction direction, Piece otherPiece);

    protected void checkSameTeam(Piece otherPiece) {
        if (isSameColor(otherPiece.color)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_SAME_TEAM);
        }
    }

    public boolean isSameColor(Color color) {
        return color == this.color;
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

    public double getScore() {
        return score;
    }

    public String getColor() {
        return color.getName();
    }

    public String getType() {
        return type;
    }
}
