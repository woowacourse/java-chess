package chessgame.domain.piece;

import chessgame.domain.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Test
    @DisplayName("칸은 8개가 존재한다")
    void getSquare() {
        Rank rank = new Rank(0, 8);

        assertThat(rank.getPieces()).hasSize(8);
    }
}
