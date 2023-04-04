package view;

import java.util.Optional;

public class Winner {
    private final String name;
    private final float score;

    public static Optional<Winner> of(float blackScore, float whiteScore) {
        float blackMinusWhite = blackScore - whiteScore;
        if (blackMinusWhite < 0) {
            return Optional.of(new Winner("WHITE", Math.abs(blackMinusWhite)));
        }
        if (blackMinusWhite > 0) {
            return Optional.of(new Winner("BLACK", blackMinusWhite));
        }
        return Optional.empty();
    }

    private Winner(String name, float score) {
        this.name = name;
        this.score = score;
    }

    public float getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
