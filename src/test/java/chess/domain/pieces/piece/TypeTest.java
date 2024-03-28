package chess.domain.pieces.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("타입")
class TypeTest {

    @DisplayName("기물별 점수를 검증한다.")
    @Test
    void score() {
        //given & when & then
        assertAll(
                () -> assertThat(Type.KING.getScore().getValue()).isEqualTo(0),
                () -> assertThat(Type.QUEEN.getScore().getValue()).isEqualTo(9),
                () -> assertThat(Type.BISHOP.getScore().getValue()).isEqualTo(3),
                () -> assertThat(Type.KNIGHT.getScore().getValue()).isEqualTo(2.5),
                () -> assertThat(Type.ROOK.getScore().getValue()).isEqualTo(5),
                () -> assertThat(Type.PAWN.getScore().getValue()).isEqualTo(1)
        );
    }
}
