package domain.point;

import domain.exception.PointOutOfBoardException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {
    @Test
    @DisplayName("주어진 심볼에 알맞는 File 객체를 찾을 수 있다.")
    void findBySymbol() {
        assertThat(Rank.findBySymbol("5")).isEqualTo(Rank.FIVE);
    }

    @Test
    @DisplayName("유효하지 않은 심볼 값으로 파일을 찾으려 시도하면 예외가 발생한다.")
    void findByInvalidSymbol() {
        assertThatThrownBy(() -> Rank.findBySymbol("k"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주어진 랭크 값보다 한 단계 올라간 랭크 객체를 반환할 수 있다.")
    void up() {
        assertThat(Rank.FOUR.up())
                .isEqualTo(Rank.FIVE);
    }

    @Test
    @DisplayName("제일 높은 랭크에서 한 단계를 더 올리려 하면 예외가 발생한다.")
    void invalidUp() {
        assertThatThrownBy(Rank.EIGHT::up)
                .isInstanceOf(PointOutOfBoardException.class);
    }

    @Test
    @DisplayName("주어진 랭크 값보다 한 단계 내려간 랭크 객체를 반환할 수 있다.")
    void down() {
        assertThat(Rank.FIVE.down())
                .isEqualTo(Rank.FOUR);
    }

    @Test
    @DisplayName("제일 낮은 랭크에서 한 단계를 더 내리려 하면 예외가 발생한다.")
    void invalidDown() {
        assertThatThrownBy(Rank.ONE::down)
                .isInstanceOf(PointOutOfBoardException.class);
    }
}