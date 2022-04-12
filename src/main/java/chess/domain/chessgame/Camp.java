package chess.domain.chessgame;

import java.util.Arrays;
import java.util.Locale;

public enum Camp {
    WHITE("w"),
    BLACK("b"),
    NONE("");

    private final String character;

    Camp(final String character) {
        this.character = character;
    }

    public Camp switchCamp() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public static Camp getCamp(final String camp) {
        return Arrays.stream(Camp.values())
            .filter(campEnum -> camp.toLowerCase(Locale.ROOT).startsWith(campEnum.getCharacter()))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public String getCharacter() {
        return character;
    }
}
