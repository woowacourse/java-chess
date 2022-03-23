package chess.piece;

import chess.board.File;
import chess.board.Rank;

public class Piece {
    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public static Piece from(File file, Rank rank){
        return PieceGenerator.generatePiece(file, rank);
    }

    abstract public String getEmoji();
}
