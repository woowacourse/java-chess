package chess.view.matcher;

import chess.domain.position.ChessRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessRankMatcherTest {

    @DisplayName("ChessRankMatcher는 모든 ChessRank에 대한 매칭 정보를 포함한다")
    @Test
    void matchRanks() {
        for (ChessRank value : ChessRank.values()) {
            assertThat(ChessRankMatcher.isPresentRank(value)).isTrue();
        }
    }
}
