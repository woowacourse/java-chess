package chess.dto;

import chess.domain.piece.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreResponse implements Response {

    private final Map<String, String> information;
    private final String metaInformation;

    private ScoreResponse(Map<String, String> information, String metaInformation) {
        this.information = information;
        this.metaInformation = metaInformation;
    }

    public static Response of(Map<Color, Double> scores) {
        Map<String, String> information = new HashMap<>();
        double whiteScore = scores.get(Color.WHITE);
        double blackScore = scores.get(Color.BLACK);

        information.put(Color.WHITE.name(), String.format("%.1f", whiteScore));
        information.put(Color.BLACK.name(), String.format("%.1f", blackScore));

        String metaInformation = determineWinner(whiteScore, blackScore);
        return new ScoreResponse(information, metaInformation);
    }

    private static String determineWinner(double whiteScore, double blackScore) {
        String metaInformation = "TIE";
        if (whiteScore > blackScore) {
            metaInformation = Color.WHITE.name();
        }
        if (whiteScore < blackScore) {
            metaInformation = Color.BLACK.name();
        }
        return metaInformation;
    }

    @Override
    public String getInformation() {
        return information.entrySet()
                .stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String getMetaInformation() {
        return metaInformation;
    }
}
