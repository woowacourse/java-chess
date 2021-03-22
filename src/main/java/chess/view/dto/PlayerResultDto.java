package chess.view.dto;

import chess.domain.player.Player;

public class PlayerResultDto {
    private String name;
    private double score;

    private PlayerResultDto(final String name, final double score) {
        this.name = name;
        this.score = score;
    }

    public static PlayerResultDto toDto(final Player player) {
        return new PlayerResultDto(player.getName(), player.getState().pieces().calculateScore());
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
