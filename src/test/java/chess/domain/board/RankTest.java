package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Rank;

public class RankTest {
    @Test
    @DisplayName("랭크는 8가지로 이루어져 있다.")
    void countRanks() {
        assertThat(Rank.values().length).isEqualTo(8);
    }

    @Test
    @DisplayName("3가 입력되면 File.THREE을 반환한다")
    void find(){
        assertThat(Rank.find("3")).isEqualTo(Rank.THREE);
    }
}
