package chess.domain.position;

import static chess.domain.position.Rank.EIGHT;
import static chess.domain.position.Rank.FIVE;
import static chess.domain.position.Rank.FOUR;
import static chess.domain.position.Rank.ONE;
import static chess.domain.position.Rank.SEVEN;
import static chess.domain.position.Rank.SIX;
import static chess.domain.position.Rank.THREE;
import static chess.domain.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Rank;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class RankTest {


    @ParameterizedTest(name = "두 랭크 사이의 랭크들을 반환한다. 시작: {0}, 도착: {1}, 결과: {2}")
    @MethodSource("betweenSource")
    void 두_랭크_사이의_랭크를_반환한다(final Rank source, final Rank target, final List<Rank> result) {
        // expect
        assertThat(source.between(target)).containsExactlyElementsOf(result);
    }

    static Stream<Arguments> betweenSource() {
        return Stream.of(
                Arguments.of(ONE, FOUR, List.of(TWO, THREE)),
                Arguments.of(ONE, TWO, Collections.emptyList()),
                Arguments.of(ONE, ONE, Collections.emptyList()),
                Arguments.of(TWO, ONE, Collections.emptyList()),
                Arguments.of(EIGHT, FIVE, List.of(SEVEN, SIX))
        );
    }

    @ParameterizedTest(name = "입력받은 랭크와의 차이를 반환한다. 시작: {0}, 도착: {1}, 결과: {2}")
    @CsvSource({"ONE, SEVEN, -6", "TWO, TWO, 0", "SEVEN, FOUR, 3"})
    void 입력받은_랭크와의_차이를_반환한다(final Rank source, final Rank target, final int result) {
        // expect
        assertThat(source.calculateGap(target)).isEqualTo(result);
    }
}
