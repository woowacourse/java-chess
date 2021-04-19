package chess.controller.web.dto;

import chess.domain.piece.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScoreDtos {

    private final List<ScoreDto> scoreDtos;

    public ScoreDtos(Map<Color, Double> score) {
        List<ScoreDto> scoreDtos = new ArrayList<>();

        for (Color color : score.keySet()) {
            scoreDtos.add(new ScoreDto(color, score.get(color)));
        }

        this.scoreDtos = scoreDtos;
    }

    public List<ScoreDto> getScoreDtos() {
        return scoreDtos;
    }

}
