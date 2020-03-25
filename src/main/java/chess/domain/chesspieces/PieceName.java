package chess.domain.chesspieces;

import chess.domain.Player;

public enum PieceName {
    KING("K", "k"),
    QUEEN("Q", "q"),
    BISHOP("B", "b"),
    ROOK("R", "r"),
    KNIGHT("N", "n"),
    PAWN("P", "p");

    private final String blackName;
    private final String whiteName;

    PieceName(String blackName, String whiteName) {
        this.blackName = blackName;
        this.whiteName = whiteName;
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

}
