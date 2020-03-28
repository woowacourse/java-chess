package chess.domain.chesspieces;

import chess.Exceptions.IllegalPlayerException;
import chess.domain.Player;

import java.util.Objects;

public enum PieceInfo {
    KING("K", "k", 0),
    QUEEN("Q", "q", 9),
    BISHOP("B", "b", 3),
    ROOK("R", "r", 5),
    KNIGHT("N", "n", 2.5),
    PAWN("P", "p", 1);

    public static final double PAWN_SCORE_DIFF = 0.5;

    private final String blackName;
    private final String whiteName;
    private final double score;

    PieceInfo(String blackName, String whiteName, double score) {
        this.blackName = blackName;
        this.whiteName = whiteName;
        this.score = score;
    }

    public String getName(Player player) {
        Objects.requireNonNull(player);
        if (player == Player.WHITE){
            return this.whiteName;
        }
        if (player == Player.BLACK) {
            return this.blackName;
        }
        throw new IllegalPlayerException();
    }

    public double getScore() {
        return score;
    }
}
