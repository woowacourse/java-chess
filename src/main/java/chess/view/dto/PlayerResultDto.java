package chess.view.dto;

import chess.domain.player.Player;

public class PlayerResultDto {
    private final String name;
    private final double score;

    private PlayerResultDto(final String name, final double score) {
        this.name = name;
        this.score = score;
    }

    public static PlayerResultDto from(final Player player) {
        return new PlayerResultDto(player.getName(), player.calculateScore());
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
