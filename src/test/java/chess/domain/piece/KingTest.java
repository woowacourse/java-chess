package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @Test
    @DisplayName("킹 생성 테스트")
    void createTest(){
        assertThat(new King(Team.WHITE)).isInstanceOf(King.class);
        assertThat(new King(Team.BLACK)).isInstanceOf(King.class);
    }
}