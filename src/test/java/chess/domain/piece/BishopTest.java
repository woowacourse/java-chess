package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("비숍")
class BishopTest {

    @ParameterizedTest
    @ValueSource(strings = {"a8", "e8", "a4", "h1"})
    @DisplayName("에 대한 이동 루트가 대각선인지 판단한다.")
    void canMove(String target) {
        Bishop bishop = new Bishop(PieceColor.BLACK);

        boolean actual = bishop.canMove(Square.from("c6"), Square.from(target));

        assertThat(actual).isTrue();
    }
}
