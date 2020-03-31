package chess.domain.board;

import static chess.domain.board.Row.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RowTest {

    @ParameterizedTest
    @DisplayName("가로축에 대해 대칭")
    @MethodSource("createOppositeCase")
    void opposite(Row actual, Row expected) {
        assertThat(actual.opposite()).isEqualTo(expected);
    }

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

    @ParameterizedTest
    @DisplayName("jump Rank")
    @MethodSource("createJumpRank")
    void jump(int index, Row actual, Row expected) {
        assertThat(actual.jump(index).orElse(null)).isEqualTo(expected);
    }

    static Stream<Arguments> createJumpRank() {
        return Stream.of(
                Arguments.of(3, ONE, FOUR),
                Arguments.of(-2, EIGHT, SIX),
                Arguments.of(2, SEVEN, null),
                Arguments.of(-3, TWO, null)
        );
    }

    @ParameterizedTest
    @DisplayName("다음 Rank")
    @MethodSource("createNextRank")
    void next(Row actual, Row expected) {
        assertThat(actual.next().orElse(null)).isEqualTo(expected);
    }

    static Stream<Arguments> createNextRank() {
        return Stream.of(
                Arguments.of(ONE, TWO),
                Arguments.of(EIGHT, null)
        );
    }

    @ParameterizedTest
    @DisplayName("이전 Rank")
    @MethodSource("createPreviousRank")
    void previous(Row actual, Row expected) {
        assertThat(actual.previous().orElse(null)).isEqualTo(expected);
    }

    static Stream<Arguments> createPreviousRank() {
        return Stream.of(
                Arguments.of(TWO, ONE),
                Arguments.of(ONE, null)
        );
    }
}