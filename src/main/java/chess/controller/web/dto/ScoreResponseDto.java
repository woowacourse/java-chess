package chess.controller.web.dto;

import chess.domain.statistics.ChessGameStatistics;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ScoreResponseDto {
    private final String matchResult;
    private final Map<String, Double> colorsScore;

    public ScoreResponseDto(ChessGameStatistics gameStatistics) {
        this.matchResult = gameStatistics.getMatchResult().getText();
        this.colorsScore = gameStatistics.getColorsScore().keySet().stream()
                .collect(toMap(Enum::name, color -> gameStatistics.getColorsScore().get(color)));
    }

    public String getMatchResult() {
        return matchResult;
    }

    public Map<String, Double> getColorsScore() {
        return colorsScore;
    }
}
