package chess.domain.square;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("1~8이 아닌 Rank를 검색하면 예외가 발생한다")
    void search_rank_exception() {
        assertThatThrownBy(() -> Rank.from('9'))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1~8인 Rank를 검색하면 Enum 필드를 반환한다.")
    void search_rank() {
        assertThat(Rank.from('1')).isEqualTo(Rank.ONE);
        assertThat(Rank.from('8')).isEqualTo(Rank.EIGHT);
    }

    @Test
    @DisplayName("Rank를 움직이면 새로운 Rank를 리턴한다.")
    void move_rank() {
        Rank three = Rank.THREE;
        assertThat(three.move(-1)).isEqualTo(Rank.TWO);
    }

    @Test
    @DisplayName("Rank를 움직이면 새로운 Rank를 리턴한다.")
    void move_rank2() {
        Rank three = Rank.THREE;
        assertThat(three.move(1)).isEqualTo(Rank.FOUR);
    }
}
