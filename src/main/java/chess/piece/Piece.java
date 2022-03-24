package chess.piece;

import chess.board.File;
import chess.board.Rank;
import chess.board.Square;

public abstract class Piece {
    protected final Color color;

    Piece(Color color) {
        this.color = color;
    }

    public static Piece from(File file, Rank rank) {
        return PieceGenerator.generatePiece(file, rank);
    }

    abstract public String getEmoji();

    abstract public boolean canMove(Square source, Square target);
}
