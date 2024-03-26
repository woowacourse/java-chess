package domain.board;

import domain.piece.Color;
import domain.position.Rank;

import java.util.Arrays;
import java.util.List;

public enum InitialColorPosition {

    WHITE(Color.WHITE, List.of(Rank.ONE, Rank.TWO)),
    BLACK(Color.BLACK, List.of(Rank.SEVEN, Rank.EIGHT)),
    NONE(Color.NONE, List.of()),
    ;

    private final Color color;
    private final List<Rank> ranks;

    InitialColorPosition(Color color, List<Rank> ranks) {
        this.color = color;
        this.ranks = ranks;
    }

    public static Color find(Rank rank) {
        return Arrays.stream(values())
                .filter((initialColorPosition -> initialColorPosition.ranks.contains(rank)))
                .findFirst()
                .orElse(InitialColorPosition.NONE)
                .color;
    }
}
