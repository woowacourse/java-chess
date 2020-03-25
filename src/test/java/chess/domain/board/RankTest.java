package chess.domain.board;

import static chess.domain.board.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    static Stream<Arguments> createOppositeCase() {
        return Stream.of(
                Arguments.of(ONE, EIGHT),
                Arguments.of(TWO, SEVEN),
                Arguments.of(THREE, SIX),
                Arguments.of(FOUR, FIVE),
                Arguments.of(FIVE, FOUR),
                Arguments.of(SIX, THREE),
                Arguments.of(SEVEN, TWO),
                Arguments.of(EIGHT, ONE)
        );
    }

    static Stream<Arguments> createNextRank() {
        return Stream.of(
                Arguments.of(ONE, TWO),
                Arguments.of(EIGHT, null)
        );
    }

    static Stream<Arguments> createPreviousRank() {
        return Stream.of(
                Arguments.of(TWO, ONE),
                Arguments.of(ONE, null)
        );
    }

    @ParameterizedTest
    @DisplayName("가로축에 대해 대칭")
    @MethodSource("createOppositeCase")
    void opposite(Rank actual, Rank expected) {
        assertThat(actual.opposite()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("다음 Rank")
    @MethodSource("createNextRank")
    void next(Rank actual, Rank expected) {
        assertThat(actual.next().orElse(null)).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("이전 Rank")
    @MethodSource("createPreviousRank")
    void previous(Rank actual, Rank expected) {
        assertThat(actual.previous().orElse(null)).isEqualTo(expected);
    }
}