package chess.domain.dto;

import chess.domain.board.Team;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class PointDto {
    private final EnumMap<Team, Double> result;

    public PointDto(EnumMap<Team, Double> result) {
        this.result = result;
    }

    public Set<Map.Entry<Team, Double>> result() {
        return new EnumMap<Team, Double>(result).entrySet();
    }
}
