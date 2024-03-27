package chess.domain.pieces.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("타입")
class TypeTest {

    @DisplayName("기물별 점수를 검증한다.")
    @Test
    void score() {
        //given
        assertThat(Type.KING.getScore().getValue()).isEqualTo(0);

        //when

        //then

    }
}
