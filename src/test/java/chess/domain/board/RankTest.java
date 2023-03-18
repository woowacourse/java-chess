package chess.domain.board;

import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.FIVE;
import static chess.domain.board.Rank.FOUR;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.SIX;
import static chess.domain.board.Rank.THREE;
import static chess.domain.board.Rank.TWO;
import static chess.domain.board.Rank.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class RankTest {

    @ParameterizedTest(name = "1 부터 8 사이 값이 아니라면 예외를 던진다. 입력: {0}")
    @ValueSource(strings = {"", "0", "9", "가비"})
    void _1_부터_8_사이_값이_아니라면_예외를_던진다(final String command) {
        // expect
        assertThatThrownBy(() -> from(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("랭크는 1 ~ 8 사이의 값이어야 합니다.");
    }

    @ParameterizedTest(name = "1 부터 8 사이 값을 입력받으면 Rank을 반환한다 입력: {0}, 출력: {1}")
    @CsvSource({"1, ONE", "8, EIGHT"})
    void _1_부터_8_사이_값을_입력받으면_Rank을_반환한다(final String command, final Rank rank) {
        // expect
        assertThat(from(command)).isEqualTo(rank);
    }

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
