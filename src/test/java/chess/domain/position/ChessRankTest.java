package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @DisplayName("랭크와 랭크 사이의 랭크들을 조회한다.")
    @Test
    void findRankBetween() {
        // given
        ChessRank start = ChessRank.ONE;
        ChessRank end = ChessRank.EIGHT;
        List<ChessRank> expected = List.of(ChessRank.TWO, ChessRank.THREE, ChessRank.FOUR, ChessRank.FIVE, ChessRank.SIX, ChessRank.SEVEN);

        // when
        List<ChessRank> rankBetween = ChessRank.findBetween(start, end);

        // then
        assertThat(expected).containsAll(rankBetween);
    }
}