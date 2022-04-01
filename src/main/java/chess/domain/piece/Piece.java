package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Rank;

public abstract class Piece {
    private static final String ERROR_MESSAGE_POSITION_SAME_TEAM = "[ERROR] 사격 중지!! 아군이다!! ><";

    final Color color;
    final double score;

    Piece(Color color, double score) {
        this.color = color;
        this.score = score;
    }

    public static Piece from(File file, Rank rank) {
        return PieceGenerator.generatePiece(file, rank);
    }

    abstract public String getEmoji();

    abstract public boolean canMove(Direction direction, Piece otherPiece);

    public void checkSameTeam(Piece otherPiece) {
        if (isSameColor(otherPiece.color)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_SAME_TEAM);
        }
    }

    public boolean isSameColor(Color color) {
        return color == this.color;
    }

    abstract public boolean isNone();

    abstract public boolean isPawn();

    abstract public boolean isKing();

    public double getScore() {
        return score;
    }
}
