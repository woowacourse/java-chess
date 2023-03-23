package chess.renderer;

import chess.domain.piece.Camp;

import java.util.Arrays;

public enum CampRenderer {
    BLACK("검정"),
    WHITE("흰");
    private final String output;

    CampRenderer(String output) {
        this.output = output;
    }


    public static String getCampOutput(Camp camp) {
        return renderPiece(camp).output;
    }

    private static CampRenderer renderPiece(Camp camp) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(camp.name()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
