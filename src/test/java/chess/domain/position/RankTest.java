package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        Rank actual = rank.moveVertical(index);

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
        assertThatThrownBy(() -> rank.moveVertical(index)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("랭크는 인덱스에 따라 정렬된 결과를 반환한다.")
    void ranksSorted() {
        // given & when & then
        assertThat(Rank.sorted()).isEqualTo(List.of(Rank.ONE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE,
                Rank.SIX, Rank.SEVEN, Rank.EIGHT));
    }

    @Test
    @DisplayName("랭크는 인덱스에 따라 내림차순 정렬된 결과를 반환한다.")
    void ranksReverseSorted() {
        // given & when & then
        assertThat(Rank.reversed()).isEqualTo(List.of(Rank.EIGHT, Rank.SEVEN, Rank.SIX, Rank.FIVE, Rank.FOUR,
                Rank.THREE, Rank.TWO, Rank.ONE));
    }
}
