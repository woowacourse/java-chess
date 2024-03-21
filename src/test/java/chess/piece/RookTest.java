package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.board.Direction;
import chess.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

class RookTest {

    @Test
    @DisplayName("룩은 상하좌우 방향으로 이동할 수 있다.")
    void rookMoveTest() {
        // given
        Rook rook = new Rook(Color.WHITE);
        Position source = Position.of("d", 4);
        // when, then
        assertAll(
                () -> assertThat(rook.isMovable(source, Position.of("d", 8))).isTrue(),
                () -> assertThat(rook.isMovable(source, Position.of("d", 1))).isTrue(),
                () -> assertThat(rook.isMovable(source, Position.of("a", 4))).isTrue(),
                () -> assertThat(rook.isMovable(source, Position.of("h", 4))).isTrue()
        );
    }

    @ParameterizedTest
    @EnumSource(value = Direction.class, mode = Mode.MATCH_NONE, names = {".*SAME.*", "KNIGHT"})
    @DisplayName("룩이 이동할 수 없는 경우를 판단한다.")
    void rookInvalidMoveTest(Direction direction) {
        // given
        Rook rook = new Rook(Color.WHITE);
        Position source = Position.of("d", 4);
        Position destination = direction.nextPosition(source);
        // when, then
        assertThat(rook.isMovable(source, destination)).isFalse();
    }
}
