package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("비숍")
class BishopTest {

    @ParameterizedTest
    @ValueSource(strings = {"a8", "e8", "a4", "h1"})
    @DisplayName("에 대한 이동 루트가 대각선인지 판단한다.")
    void canMove(String target) {
        Bishop bishop = new Bishop(PieceColor.BLACK);

        boolean actual = bishop.canMove(Position.from("c6"), Position.from(target));

        assertThat(actual).isTrue();
    }
}
