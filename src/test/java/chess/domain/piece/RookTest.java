package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @Test
    @DisplayName("룩 생성 테스트")
    void createTest(){
        assertThat(new Rook(Team.BLACK)).isInstanceOf(Rook.class);
        assertThat(new Rook(Team.WHITE)).isInstanceOf(Rook.class);
    }
}