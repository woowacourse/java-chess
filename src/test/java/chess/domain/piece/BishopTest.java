package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @Test
    @DisplayName("비숍 생성 테스트")
    void createTest(){
        assertThat(new Bishop(Team.WHITE)).isInstanceOf(Bishop.class);
        assertThat(new Bishop(Team.BLACK)).isInstanceOf(Bishop.class);
    }
}