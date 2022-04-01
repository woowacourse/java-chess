package chess.model.square;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.model.square.Rank;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    void availableLocation() {
        assertAll(
                () -> assertThat(Rank.FOUR.availableLocation(-3)).isTrue(),
                () -> assertThat(Rank.FOUR.availableLocation(4)).isTrue()
        );
    }

    @Test
    void unAvailableLocation() {
        assertAll(
                () -> assertThat(Rank.FOUR.availableLocation(-4)).isFalse(),
                () -> assertThat(Rank.FOUR.availableLocation(5)).isFalse()
        );
    }

    @Test
    void nextTo() {
        assertAll(
                () -> assertThat(Rank.FOUR.nextTo(-3)).isEqualTo(Rank.ONE),
                () -> assertThat(Rank.FOUR.nextTo(4)).isEqualTo(Rank.EIGHT)
        );
    }

    @Test
    void nextToAssert() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> Rank.FOUR.nextTo(-4)),
                () -> assertThrows(IllegalArgumentException.class, () -> Rank.FOUR.nextTo(5))
        );
    }
}
