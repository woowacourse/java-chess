package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("룩")
class RookTest {

    @ParameterizedTest
    @ValueSource(strings = {"a6", "h6", "c1", "c8"})
    @DisplayName("에 대한 이동 루트가 상하좌우로 이동하는지 판단한다.")
    void canMove(String target) {
        Rook rook = new Rook(PieceColor.BLACK);

        boolean actual = rook.canMove(Position.from("c6"), Position.from(target));

        assertThat(actual).isTrue();
    }
}
