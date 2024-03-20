package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("가장 높은 순서 값을 찾는다.")
    @Test
    void maxTest() {
        assertThat(Rank.max()).isEqualTo(7);
    }

    @DisplayName("순서를 받아 열을 찾는다.")
    @Test
    void findTest() {
        Assertions.assertAll(
                () -> assertThat(Rank.find(0)).isEqualTo(Rank.A),
                () -> assertThat(Rank.find(1)).isEqualTo(Rank.B),
                () -> assertThat(Rank.find(2)).isEqualTo(Rank.C),
                () -> assertThat(Rank.find(3)).isEqualTo(Rank.D),
                () -> assertThat(Rank.find(4)).isEqualTo(Rank.E),
                () -> assertThat(Rank.find(5)).isEqualTo(Rank.F),
                () -> assertThat(Rank.find(6)).isEqualTo(Rank.G),
                () -> assertThat(Rank.find(7)).isEqualTo(Rank.H)
        );
    }
}
