package chess.domain.piece;

import chess.domain.board.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
        assertThatThrownBy(() -> Rank.from(rank))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 생성시_8보다_큰_값이면_예외() {
        // when & then
        assertThatThrownBy(() -> Rank.from(9))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "1 ~ 8 사이의 값으로(ex: {0}) 생성된다.")
    @ValueSource(ints = {1, 5, 8})
    void 정상_생성(final int rank) {
        // when & then
        assertDoesNotThrow(() -> Rank.from(rank));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    void 값이_같으면_동등하다(final int value) {
        // given
        final Rank rank1 = Rank.from(value);
        final Rank rank2 = Rank.from(value);

        // when & then
        assertThat(rank1).isEqualTo(rank2);
    }
}
