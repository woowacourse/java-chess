package chess.domain.board;

import static chess.domain.board.Rank.*;
import static org.assertj.core.api.Assertions.*;

import chess.domain.board.Rank;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("역순 정렬 테스트")
    void test() {
        List<Rank> reverseValues = Rank.reverseValues();
        assertThat(reverseValues).isEqualTo(List.of(EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO, ONE));
    }

    @Test
    @DisplayName("두 Rank 사이에 존재하는 모든 Rank을 List로 담아 전달한다.")
    void traceGroup() {
        //given
        List<Rank> ranks = Rank.traceGroup(Rank.ONE, FOUR);

        //then
        assertThat(ranks).isEqualTo(List.of(Rank.TWO, Rank.THREE));
    }
}
