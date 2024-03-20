package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("랭크")
class RankTest {

    @DisplayName("랭크는 인덱스만큼 전진한다.")
    @Test
    void forwardByIndex() {
        // given
        Rank rank = Rank.SIX;
        int index = 2;

        // when
        Rank actual = rank.forward(index);

        // then
        assertThat(actual).isEqualTo(Rank.EIGHT);
    }

    @DisplayName("랭크는 전진 시 범위를 벗어나면 예외가 발생한다.")
    @Test
    void forwardByIndexException() {
        // given
        Rank rank = Rank.SIX;
        int index = 3;

        // when & then
        assertThatThrownBy(() -> rank.forward(index)).isInstanceOf(IndexOutOfBoundsException.class);
    }
}