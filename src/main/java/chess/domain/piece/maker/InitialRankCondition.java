package chess.domain.piece.maker;

import chess.domain.piece.property.Color;
import chess.domain.position.Rank;

import java.util.Map;

import static chess.domain.piece.property.Color.BLACK;
import static chess.domain.piece.property.Color.WHITE;
import static chess.domain.position.Rank.EIGHT;
import static chess.domain.position.Rank.ONE;
import static chess.domain.position.Rank.SEVEN;
import static chess.domain.position.Rank.TWO;
import static java.util.Map.entry;

public enum InitialRankCondition {
    PAWN(Map.ofEntries(entry(BLACK, SEVEN), entry(WHITE, TWO))),
    NON_PAWN(Map.ofEntries(entry(BLACK, EIGHT), entry(WHITE, ONE)));

    private final Map<Color, Rank> rankByColor;

    InitialRankCondition(final Map<Color, Rank> rankByColor) {
        this.rankByColor = rankByColor;
    }

    public Rank byColor(final Color color) {
        return rankByColor.get(color);
    }
}
