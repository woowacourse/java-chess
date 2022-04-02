package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Movement;
import chess.domain.position.Row;

public abstract class Piece {
    private static final String ERROR_MESSAGE_POSITION_SAME_TEAM = "[ERROR] 사격 중지!! 아군이다!! ><";

    final Color color;
    final double score;

    Piece(Color color, double score) {
        this.color = color;
        this.score = score;
    }

    public static Piece from(Column column, Row row) {
        return PieceGenerator.generatePiece(column, row);
    }

    public abstract String getEmoji();

    public abstract boolean canMove(Movement movement, Piece otherPiece);

    public void checkSameTeam(Piece otherPiece) {
        if (isSameColor(otherPiece.color)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_SAME_TEAM);
        }
    }

    public boolean isSameColor(Color color) {
        return color == this.color;
    }

    public abstract boolean isNone();

    public abstract boolean isPawn();

    public abstract boolean isKing();

    public double getScore() {
        return score;
    }
}
