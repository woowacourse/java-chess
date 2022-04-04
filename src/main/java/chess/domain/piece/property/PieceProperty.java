package chess.domain.piece.property;

import java.util.List;
import java.util.stream.Collectors;

public class PieceProperty {
    private final Color color;
    private final Score score;

    private PieceProperty(Color color, Score score) {
        this.color = color;
        this.score = score;
    }

    public static PieceProperty of(Color color, double score) {
        return new PieceProperty(color, new Score(score));
    }

    public static double computeScore(List<PieceProperty> properties) {
        return Score.computeScore(
            properties.stream()
                .map(pieceProperty -> pieceProperty.score)
                .collect(Collectors.toList()));
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameColor(PieceProperty property) {
        return isSameColor(property.color);
    }

    public String getColor() {
        return color.name();
    }
}
