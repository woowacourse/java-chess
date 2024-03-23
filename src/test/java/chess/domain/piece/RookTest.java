package chess.domain.piece;

import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("룩")
class RookTest {

    @ParameterizedTest
    @ValueSource(strings = {"a6", "h6", "c1", "c8"})
    @DisplayName("상하좌우로 이동한다.")
    void move(String targetInput) {
        // given
        Square source = Square.from("c6");
        Square target = Square.from(targetInput);
        Rook rook = new Rook(PieceColor.BLACK, source);

        // when
        rook.move(target);

        // then
        assertThat(rook.getSquare()).isEqualTo(target);
    }
}
