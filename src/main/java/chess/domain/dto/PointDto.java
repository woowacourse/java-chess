package chess.domain.dto;

import chess.domain.board.Team;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class PointDto {
    private final EnumMap<Team, Double> result;

    public PointDto(EnumMap<Team, Double> result) {
        this.result = result;
    }

    public Set<Map.Entry<Team, Double>> result() {
        return new EnumMap<>(result).entrySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointDto pointDto = (PointDto) o;
        return Objects.equals(result, pointDto.result);
    }

    @Override
    public int hashCode() {
        return result != null ? result.hashCode() : 0;
    }
}
