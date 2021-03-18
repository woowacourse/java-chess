package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @DisplayName("Rank 열거 객체의 value를 비교한다")
    @Test
    void compareRankValue() {
        Rank rank = Rank.FIVE;

        assertThat(rank.getY()).isEqualTo(5);
    }

    @DisplayName("Rank가 1인 경우 값을 차감할 수 없다")
    @Test
    void cannotDecreaseRank() {
        Rank rank = Rank.ONE;

        assertThatCode(rank::decrease)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("잘못된 rank값 입니다.");
    }

    @DisplayName("Rank가 1이 아닌 경우 메서드를 호출하면 1이 차감된 Rank가 반환된다")
    @Test
    void decreaseRank() {
        Rank rank = Rank.TWO;
        Rank decreasedRank = rank.decrease();

        assertThat(decreasedRank).isEqualTo(Rank.ONE);
    }
}