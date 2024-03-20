package domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessRankTest {

    @DisplayName("주어진 Rank의 값으로 Rank을 찾는다.")
    @Test
    void findByValue() {
        // given
        String rankValue = "1";

        // when
        ChessRank rank = ChessRank.findByValue(rankValue);

        // then
        assertThat(rank).isEqualTo(ChessRank.ONE);
    }

    @DisplayName("체스 랭크 범위에 해당하지 않는 값을 조회하면 예외를 발생시킨다.")
    @Test
    void unknownChessRankValue() {
        // given
        String rankValue = "0";

        // when & then
        assertThatThrownBy(() -> ChessRank.findByValue(rankValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("체스 랭크 범위에 해당하지 않는 값입니다.");
    }
}