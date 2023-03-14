package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PieceTest {

    @Test
    @DisplayName("기물을 캐싱한다.")
    void createPiece_thenReturnCache() {
        //given
        Piece piece = Piece.of(Type.KING, Color.BLACK);

        //when

        //then
        assertThat(piece == Piece.of(Type.KING, Color.BLACK)).isTrue();
    }
    @Test
    @DisplayName("타입과 색깔을 넣어주면 기물이 생성된다.")
    void givenColorAndType_thenCreate() {
        //then
        assertThat(Piece.of(Type.KING, Color.BLACK))
                .extracting("type", "color")
                .containsExactly(Type.KING, Color.BLACK);
    }
}
