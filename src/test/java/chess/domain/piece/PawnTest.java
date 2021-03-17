package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Test
    @DisplayName("폰 생성 테스트")
    void createTest(){
        assertThat(new Pawn(Team.WHITE)).isInstanceOf(Pawn.class);
        assertThat(new Pawn(Team.BLACK)).isInstanceOf(Pawn.class);
    }
}