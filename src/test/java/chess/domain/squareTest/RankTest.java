package chess.domain.squareTest;

import chess.domain.square.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RankTest {
    @Test
    @DisplayName("Rank이 같은 객체를 실제 같은 Rank로 인식하는지 확인")
    void checkSameRank() {
        Rank rank = Rank.of(7);
        assertThat(rank).isEqualTo(Rank.of(7));
    }

    @Test
    @DisplayName("범위 바깥의 Rank.of를 부를 때 exception 확인")
    void checkException() {
        assertThatThrownBy(() -> Rank.of(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된");
    }

    @Test
    @DisplayName("Rank이 증가했을 때 범위 안에 있는지 체크")
    void validateIncrementBy() {
        Rank rank = Rank.of(3);
        assertThat(rank.validateIncrementNumber(-3)).isFalse();
        assertThat(rank.validateIncrementNumber(5)).isTrue();
    }
}
