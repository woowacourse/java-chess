package chess.domain.piece;

import java.util.Arrays;

public enum PlayingCamp {
    WHITE("white"),
    BLACK("black");

    private final String color;

    PlayingCamp(String color) {
        this.color = color;
    }

    public PlayingCamp next() {
        if (this == PlayingCamp.BLACK) return PlayingCamp.WHITE;
        return PlayingCamp.BLACK;
    }

    public static PlayingCamp from(String color) {
        return Arrays.stream(PlayingCamp.values())
                .filter(c -> c.color.equals(color))
                .findAny().orElseThrow(() -> new IllegalArgumentException("존재하지 않는 색입니다."));
    }

    public String getName() {
        return this.color;
    }
}
