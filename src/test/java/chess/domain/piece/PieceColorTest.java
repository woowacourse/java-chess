package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("기물 색상")
class PieceColorTest {

    @Test
    @DisplayName("흰색을 반대색으로 변환한다.")
    void oppositeTest() {
        // given
        PieceColor pieceColor = PieceColor.WHITE;

        // when
        PieceColor oppositeColor = pieceColor.opposite();

        // then
        assertThat(oppositeColor).isEqualTo(PieceColor.BLACK);
    }
}
