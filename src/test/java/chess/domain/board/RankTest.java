package chess.domain.board;

import static chess.domain.board.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @ParameterizedTest
    @DisplayName("가로축에 대해 대칭")
    @MethodSource("createOppositeCase")
    void opposite(Rank actual, Rank expected) {
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
}