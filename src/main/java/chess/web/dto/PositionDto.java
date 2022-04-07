package chess.web.dto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

import chess.domain.board.Position;
import java.util.Arrays;
import java.util.Map;

public final class PositionDto {

    private final Map<String, String> value;

    private PositionDto(final Map<String, String> value) {
        this.value = value;
    }

    public static PositionDto of(final String body) {
        return Arrays.stream(body.strip().split("\n"))
                .map(s -> s.split("="))
                .collect(collectingAndThen(toMap(PositionDto::extractKey, PositionDto::extractValue), PositionDto::new));
    }

    private static String extractKey(final String[] value) {
        return value[0];
    }

    private static String extractValue(final String[] value) {
        return value[1];
    }

    public Position get(String key) {
        return Position.from(value.get(key).strip());
    }
}

