package chess.domain.piece;

import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("비숍")
class BishopTest {

    @ParameterizedTest
    @ValueSource(strings = {"a8", "e8", "a4", "h1"})
    @DisplayName("대각선으로 이동한다.")
    void move(String targetInput) {
        // given
        Square source = Square.from("c6");
        Square target = Square.from(targetInput);
        Bishop bishop = new Bishop(PieceColor.BLACK, source);

        // when
        bishop.move(target);

        // then
        assertThat(bishop.getSquare()).isEqualTo(target);
    }
}
