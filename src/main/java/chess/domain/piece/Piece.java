package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Rank;

public abstract class Piece {
    protected final Color color;

    Piece(Color color) {
        this.color = color;
    }

    public static Piece from(File file, Rank rank) {
        return PieceGenerator.generatePiece(file, rank);
    }

    abstract public String getEmoji();

    abstract public boolean canMove(Direction direction);

    abstract public boolean isPawn();

    public boolean isSameTeam(Piece otherPiece) {
        return this.color == otherPiece.color;
    }
}
