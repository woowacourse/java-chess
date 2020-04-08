package chess.domain.game;

import java.util.Objects;

public enum Player {
    WHITE, BLACK;

    public static Player of(String player) {
        Objects.requireNonNull(player, "Player가 없습니다.");

        try {
            return Player.valueOf(player);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Player를 찾을 수 없습니다.");
        }
    }

    public static Player reversePlayer(Player turn) {
        if (turn == Player.WHITE) {
            return Player.BLACK;
        }
        return Player.WHITE;
    }
}
