package chess.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

public class ScoresDto {
    private final List<ScoreDto> scores;

    public ScoresDto(final PointDto pointDto) {
        scores = convertTo(pointDto);
    }

    private List<ScoreDto> convertTo(PointDto pointDto) {
        return pointDto.result().stream()
                .map(entry -> new ScoreDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<ScoreDto> scores() {
        return scores;
    }
}
