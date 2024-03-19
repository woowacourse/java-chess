package domain;

import java.util.List;

public class RankGenerator {
    private static final List<String> SAVED_RANKS = List.of(
            "RNBQKBNR",
            "PPPPPPPP",
            "........",
            "........",
            "........",
            "........",
            "pppppppp",
            "rnbqkbnr"
    );

    public RankGenerator() {

    }

    public String generate(final int index) {
        return SAVED_RANKS.get(index);
    }
}
