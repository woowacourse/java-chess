package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Test
    @DisplayName("문자열 Rank 값을 입력할 때 해당 값을 가지는 Column 객체가 반환된다.")
    void shouldSucceedFindingColumn() {

        String rankValue = "1";

        assertThat(Rank.findRank(rankValue)).isEqualTo(Rank.ONE);
    }

    @Test
    @DisplayName("체스판 범위에 포함된 rank값이 입력됐을 때 true를 반환한다.")
    void shouldSucceedToCheckInRange() {
        int rank = 4;

        assertThat(Rank.isInChessBoardRange(rank)).isTrue();
    }

    @Test
    @DisplayName("체스판 범위를 벗어난 rank값이 입력되었을 때 false를 반환한다.")
    void shouldSucceedToCheckOutOfRange() {
        int rank = 9;

        assertThat(Rank.isInChessBoardRange(rank)).isFalse();
    }
}