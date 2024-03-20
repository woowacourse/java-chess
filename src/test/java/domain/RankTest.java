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
                () -> assertThat(Rank.find(0)).isEqualTo(Rank.ONE),
                () -> assertThat(Rank.find(1)).isEqualTo(Rank.TWO),
                () -> assertThat(Rank.find(2)).isEqualTo(Rank.THREE),
                () -> assertThat(Rank.find(3)).isEqualTo(Rank.FOUR),
                () -> assertThat(Rank.find(4)).isEqualTo(Rank.FIVE),
                () -> assertThat(Rank.find(5)).isEqualTo(Rank.SIX),
                () -> assertThat(Rank.find(6)).isEqualTo(Rank.SEVEN),
                () -> assertThat(Rank.find(7)).isEqualTo(Rank.EIGHT)
        );
    }
}
