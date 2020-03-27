package chess;

import chess.domain.Player;

public class Status {
    private Player player;
    private double score;

    public Status(Player player, double score) {
        this.player = player;
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public double getScore() {
        return score;
    }
}
