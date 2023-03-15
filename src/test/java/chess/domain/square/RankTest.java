package chess.domain.square;

import chess.domain.square.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RankTest {

    @Test
    @DisplayName("1~8이 아닌 Rank를 검색하면 예외가 발생한다")
    void search_rank_exception() {
        assertThrows(IllegalArgumentException.class,
                () -> Rank.from('9'));
    }

    @Test
    @DisplayName("1~8인 Rank를 검색하면 Enum 필드를 반환한다.")
    void search_rank() {
        assertThat(Rank.from('1')).isEqualTo(Rank.ONE);
        assertThat(Rank.from('8')).isEqualTo(Rank.EIGHT);
    }
}