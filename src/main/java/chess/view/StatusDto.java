package chess.view;

import chess.domain.game.Game;
import chess.domain.pieces.Color;
import chess.machine.Result;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class StatusDto {

    private final Map<String, Double> score;

    private StatusDto(Map<String, Double> score) {
        this.score = score;
    }

    public static StatusDto of(Game game) {
        return new StatusDto(Arrays.stream(Color.values())
                .collect(Collectors.toMap(Enum::name, game::calculateScore)));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String color : score.keySet()) {
            sb.append("\"").append(color).append("\" : ").append(score.get(color)).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }
}
