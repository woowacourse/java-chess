package chess.domain.game;

public enum Player {
    WHITE, BLACK;

    public static Player reversePlayer(Player turn) {
        if (turn == Player.WHITE) {
            return Player.BLACK;
        }
        return Player.WHITE;
    }
}
