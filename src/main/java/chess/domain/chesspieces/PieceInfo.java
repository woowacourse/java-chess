package chess.domain.chesspieces;

import chess.Exceptions.IllegalPlayerException;
import chess.domain.Player;

import java.util.Objects;

public enum PieceInfo {
    KING("K", 0),
    QUEEN("Q", 9),
    BISHOP("B",  3),
    ROOK("R", 5),
    KNIGHT("N",  2.5),
    PAWN("P",  1),
    EMPTY(".", 0);

    public static final double PAWN_SCORE_DIFF = 0.5;

    private static final String EMPTY_NAME = ".";
    private final String name;
    private final double score;


    PieceInfo(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public final String getName(Player player) {
        Objects.requireNonNull(player);
        if (player == Player.WHITE){
            return name.toLowerCase();
        }
        if (player == Player.BLACK) {
            return name.toUpperCase();
        }
        if (player == Player.NONE) {
            return EMPTY_NAME;
        }
        throw new IllegalPlayerException();
    }

    public double getScore() {
        return score;
    }
}
