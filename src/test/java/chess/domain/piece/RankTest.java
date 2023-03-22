package chess.domain.piece;

import chess.domain.piece.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Rank 은")
class RankTest {

    @ParameterizedTest(name = "생성 시 1보다 작은 값(ex: {0})이면 예외")
    @ValueSource(ints = {-1, 0})
    void 생성시_1보다_작은_값이면_예외(final int rank) {
        // when & then
        assertThatThrownBy(() -> new Rank(rank))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 생성시_8보다_큰_값이면_예외() {
        // when & then
        assertThatThrownBy(() -> new Rank(9))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "1 ~ 8 사이의 값으로(ex: {0}) 생성된다.")
    @ValueSource(ints = {1, 5, 8})
    void 정상_생성(final int rank) {
        // when & then
        assertDoesNotThrow(() -> new Rank(rank));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    void 값이_같으면_동등하다(final int value) {
        // given
        final Rank rank1 = new Rank(value);
        final Rank rank2 = new Rank(value);

        // when & then
        assertThat(rank1).isEqualTo(rank2);
    }

    @ParameterizedTest(name = "Rank 사이의 간격을 구할 수 있다. 현재[{0}]인 경우 목적지인 [{1}] 과의 차이는 [{2}] 이다.")
    @CsvSource({
            "2,1,-1",
            "3,1,-2",
            "1,3,2",
            "1,2,1",
            "1,1,0",
            "3,3,0",
    })
    void Rank_사이의_간격을_구할_수_있다(final int currentRank, final int destination, final int distance) {
        // given
        final Rank from = new Rank(currentRank);
        final Rank dest = new Rank(destination);

        // when & then
        assertThat(from.interval(dest)).isEqualTo(distance);
    }

    @Test
    void 특정_거리를_더할_수_있다() {
        // given
        final Rank from = new Rank(3);

        // when & then
        assertThat(from.plus(-2)).isEqualTo(new Rank(1));
    }
}
