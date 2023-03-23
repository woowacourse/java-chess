package chess.controller;

import static java.util.stream.Collectors.toList;

import chess.model.Scores;
import java.util.List;

public class ScoreResponses {

    private final List<String> responses;

    private ScoreResponses(final List<String> responses) {
        this.responses = responses;
    }

    public static ScoreResponses from(final Scores scores) {
        final List<String> responses = scores.getScoreBoard().stream()
                .map(score -> String.format("%s : %s점", score.getColor().name(), score.getScore()))
                .collect(toList());

        return new ScoreResponses(responses);
    }

    public List<String> getResponses() {
        return responses;
    }
}
