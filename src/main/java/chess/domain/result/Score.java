package chess.domain.result;

import chess.domain.game.Player;

import java.util.Objects;

public class Score {
    private Player player;
    private double score;

    public Score(Player player, double score) {
        Objects.requireNonNull(player);
        Objects.requireNonNull(score);
        
        this.player = player;
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public double getScore() {
        return score;
    }

    public boolean isSameScore(double score) {
        return this.score == score;
    }
}
