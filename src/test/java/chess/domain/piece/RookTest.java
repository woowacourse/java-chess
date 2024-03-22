package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
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
        Position source = Position.of(File.D, Rank.FOUR);
        // when, then
        assertAll(
                () -> assertThat(rook.isMovable(source, Position.of(File.D, Rank.EIGHT))).isTrue(),
                () -> assertThat(rook.isMovable(source, Position.of(File.D, Rank.ONE))).isTrue(),
                () -> assertThat(rook.isMovable(source, Position.of(File.A, Rank.FOUR))).isTrue(),
                () -> assertThat(rook.isMovable(source, Position.of(File.H, Rank.FOUR))).isTrue()
        );
    }

    @ParameterizedTest
    @EnumSource(value = Direction.class, mode = Mode.MATCH_NONE, names = {".*SAME.*", "KNIGHT"})
    @DisplayName("룩이 이동할 수 없는 경우를 판단한다.")
    void rookInvalidMoveTest(Direction direction) {
        // given
        Rook rook = new Rook(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        Position destination = direction.nextPosition(source);
        // when, then
        assertThat(rook.isMovable(source, destination)).isFalse();
    }
}
