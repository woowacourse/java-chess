package chess.dto;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import chess.domain.Color;

public class PlayerScoresDto {

    private final Map<String, Double> playerScores;

    private PlayerScoresDto(final Map<String, Double> playerScores) {
        this.playerScores = playerScores;
    }

    public static PlayerScoresDto toDto(final Map<Color, Double> playerScores) {
        return new PlayerScoresDto(playerScores.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        Entry::getValue
                )));
    }

    public Map<String, Double> getPlayerScores() {
        return playerScores;
    }
}
