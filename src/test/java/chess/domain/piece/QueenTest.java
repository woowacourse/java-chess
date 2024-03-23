package chess.domain.piece;

import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("퀸")
class QueenTest {

    @ParameterizedTest
    @ValueSource(strings = {"a8", "c8", "e8", "h6", "h1", "c1", "a4", "a6"})
    @DisplayName("상하좌우 또는 대각선 으로 이동한다.")
    void move(String targetInput) {
        // given
        Square source = Square.from("c6");
        Square target = Square.from(targetInput);
        Queen queen = new Queen(PieceColor.BLACK, source);

        // when
        queen.move(target);

        // then
        assertThat(queen.getSquare()).isEqualTo(target);
    }
}
