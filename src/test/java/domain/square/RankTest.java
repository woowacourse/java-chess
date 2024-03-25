package domain.square;

import chess.domain.square.Rank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("가장 높은 순서 값을 찾는다.")
    @Test
    void maxTest() {
        assertThat(Rank.max()).isEqualTo(8);
    }

    @DisplayName("순서를 받아 열을 찾는다.")
    @Test
    void findTest() {
        Assertions.assertAll(
                () -> assertThat(Rank.find(1)).isEqualTo(Rank.ONE),
                () -> assertThat(Rank.find(2)).isEqualTo(Rank.TWO),
                () -> assertThat(Rank.find(3)).isEqualTo(Rank.THREE),
                () -> assertThat(Rank.find(4)).isEqualTo(Rank.FOUR),
                () -> assertThat(Rank.find(5)).isEqualTo(Rank.FIVE),
                () -> assertThat(Rank.find(6)).isEqualTo(Rank.SIX),
                () -> assertThat(Rank.find(7)).isEqualTo(Rank.SEVEN),
                () -> assertThat(Rank.find(8)).isEqualTo(Rank.EIGHT)
        );
    }

    @DisplayName("source order가 작을 때 source 위치와 target 위치 사이의 Rank 리스트를 찾는다.")
    @Test
    void findBetweenAscTest() {
        Rank currentRank = Rank.ONE;
        Rank targetRank = Rank.FOUR;

        List<Rank> ranks = currentRank.findBetween(targetRank);

        assertThat(ranks).containsExactly(Rank.TWO, Rank.THREE);
    }

    @DisplayName("source order가 클 때 source 위치와 target 위치 사이의 Rank 리스트를 찾는다.")
    @Test
    void findBetweenDescTest() {
        Rank currentRank = Rank.FOUR;
        Rank targetRank = Rank.ONE;

        List<Rank> ranks = currentRank.findBetween(targetRank);

        assertThat(ranks).containsExactly(Rank.THREE, Rank.TWO);
    }
}
