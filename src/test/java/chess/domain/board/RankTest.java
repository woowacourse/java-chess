package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class RankTest {

    @ParameterizedTest(name = "1 부터 8 사이 값이 아니라면 예외를 던진다. 입력: {0}")
    @ValueSource(strings = {"", "0", "9", "가비"})
    void _1_부터_8_사이_값이_아니라면_예외를_던진다(final String command) {
        // expect
        assertThatThrownBy(() -> Rank.from(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("랭크는 1 ~ 8 사이의 값이어야 합니다.");
    }

    @ParameterizedTest(name = "1 부터 8 사이 값을 입력받으면 Rank을 반환한다 입력: {0}, 출력: {1}")
    @CsvSource({"1, ONE", "8, EIGHT"})
    void _1_부터_8_사이_값을_입력받으면_Rank을_반환한다(final String command, final Rank rank) {
        // expect
        assertThat(Rank.from(command)).isEqualTo(rank);
    }

    @ParameterizedTest(name = "숫자 1 부터 8 사이 값이 아니라면 예외를 던진다. 입력: {0}")
    @ValueSource(ints = {-1, 0, 9})
    void 숫자_1_부터_8_사이_값이_아니라면_예외를_던진다(final int position) {
        // expect
        assertThatThrownBy(() -> Rank.from(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("위치 값은 1 ~ 8 사이의 값이어야 합니다.");
    }

    @ParameterizedTest(name = "숫자 1 부터 8 사이 값을 입력받으면 Rank을 반환한다 입력: {0}, 출력: {1}")
    @CsvSource({"1, ONE", "8, EIGHT"})
    void 숫자_1_부터_8_사이_값을_입력받으면_Rank을_반환한다(final int position, final Rank rank) {
        // expect
        assertThat(Rank.from(position)).isEqualTo(rank);
    }

    @ParameterizedTest(name = "입력받은 랭크와의 차이를 반환한다. 시작: {0}, 도착: {1}, 결과: {2}")
    @CsvSource({"ONE, SEVEN, -6", "TWO, TWO, 0", "SEVEN, FOUR, 3"})
    void 입력받은_랭크와의_차이를_반환한다(final Rank source, final Rank target, final int result) {
        // expect
        assertThat(source.calculateGap(target)).isEqualTo(result);
    }
}
