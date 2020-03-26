package chess.domain.chesspieces;

import chess.domain.Player;

public enum PieceInfo {
    KING("K", "k", 0),
    QUEEN("Q", "q", 9),
    BISHOP("B", "b", 3),
    ROOK("R", "r", 5),
    KNIGHT("N", "n", 2.5),
    PAWN("P", "p", 1);

    private final String blackName;
    private final String whiteName;
    private final double score;

    PieceInfo(String blackName, String whiteName, double score) {
        this.blackName = blackName;
        this.whiteName = whiteName;
        this.score = score;
    }

    public String getName(Player player) {
        if (player == Player.WHITE){
            return this.whiteName;
        }
        if (player == Player.BLACK) {
            return this.blackName;
        }
        throw new IllegalArgumentException("처리할 수 없는 사용자입니다.");
    }

    public double getScore() {
        return score;
    }
}
