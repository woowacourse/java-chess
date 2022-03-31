package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {
    @DisplayName("랭크는 1~8 까지를 갖는다.")
    @Test
    public void rank() {
        //given
        Rank rank = Rank.ONE;

        //when
        int rankName = rank.getRank();

        //then
        assertThat(rankName).isEqualTo(1);
    }
}
