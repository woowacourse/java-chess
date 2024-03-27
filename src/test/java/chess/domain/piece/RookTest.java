package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

class RookTest {

    @ParameterizedTest
    @CsvSource(value = {"D, 8", "D, 1", "A, 4", "H, 4"})
    @DisplayName("룩은 상하좌우 방향으로 이동할 수 있다.")
    void rookMoveTest(String file, int rank) {
        // given
        Rook rook = new Rook(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        // when
        boolean movable = rook.isMovable(source, Position.of(File.from(file), Rank.from(rank)));
        // then
        assertThat(movable).isTrue();

    }

    @ParameterizedTest
    @EnumSource(value = Direction.class, mode = Mode.MATCH_NONE, names = {".*SAME.*", "KNIGHT"})
    @DisplayName("룩이 이동할 수 없는 경우를 판단한다.")
    void rookInvalidMoveTest(Direction direction) {
        // given
        Rook rook = new Rook(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        Position destination = direction.nextPosition(source);
        // when
        boolean movable = rook.isMovable(source, destination);
        // then
        assertThat(movable).isFalse();
    }
}
