package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @Test
    @DisplayName("퀸 생성 테스트")
    void createTest(){
        assertThat(new Queen(Team.WHITE)).isInstanceOf(Queen.class);
        assertThat(new Queen(Team.BLACK)).isInstanceOf(Queen.class);
    }
}