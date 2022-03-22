package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {
    @DisplayName("랭크는 a~h 까지를 갖는다.")
    @Test
    public void rank() {
        //given
        Rank rank = Rank.A;

        //when
        String rankName = rank.getRank();

        //then
        assertThat(rankName).isEqualTo("a");
    }
}
