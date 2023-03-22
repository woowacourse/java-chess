package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @DisplayName("Rank변화량으로 Rank를 이동한다")
    @Test
    void move() {
        Rank rank = Rank.TWO;

        assertThat(rank.move(2)).isSameAs(Rank.FOUR);
        assertThat(rank.move(-1)).isSameAs(Rank.ONE);
    }

    @DisplayName("인덱스로 Rank를 찾을 수 있다")
    @Test
    void from() {
        Rank rank = Rank.from(1);

        assertThat(rank).isSameAs(Rank.ONE);
    }

    @DisplayName("잘못된 인덱스로 Rank를 찾으면 얘외가 발생한다")
    @Test
    void from_throws() {
        assertThatThrownBy(() -> Rank.from(9))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 포지션입니다.");
    }
}
