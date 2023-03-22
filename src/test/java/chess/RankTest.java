package chess;

import chess.domain.Rank;
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
}