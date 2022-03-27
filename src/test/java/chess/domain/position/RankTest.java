package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    @DisplayName("Rank에 1~8이 있는지 확인한다.")
    void contain() {
        assertThat(Rank.values()).containsOnly(Rank.ONE, Rank.TWO, Rank.THREE, Rank.FOUR,
                Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT);
    }

    @Test
    @DisplayName("값을 이용해 Rank를 찾는다.")
    void findRank() {
        assertThat(Rank.of(7)).isEqualTo(Rank.SEVEN);
    }

    @Test
    @DisplayName("1~8 이외의 값이 들어오는 경우 예외를 발생시킨다.")
    void exception() {
        assertThatThrownBy(() -> Rank.of(9))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 Rank 값 입니다.");
    }

    @Test
    @DisplayName("Rank를 1 증가시킨다.")
    void plus() {
        Rank rank = Rank.of(5);
        assertThat(rank.move(1)).isEqualTo(Rank.SIX);
    }

    @Test
    @DisplayName("Rank를 1 감소시킨다.")
    void minus() {
        Rank rank = Rank.of(5);
        assertThat(rank.move(-1)).isEqualTo(Rank.FOUR);
    }

    @Test
    @DisplayName("Rank를 1 증가 시킬 때, 경계선을 넘어가면 null을 반환한다.")
    void plusOutOfBounds() {
        Rank rank = Rank.of(7);

        assertThat(rank.move(2)).isNull();
    }

    @Test
    @DisplayName("Rank를 1 감소 시킬 때, 경계선을 넘어가면 null을 반환한다.")
    void minusOutOfBounds() {
        Rank rank = Rank.of(2);

        assertThat(rank.move(-2)).isNull();
    }
}
