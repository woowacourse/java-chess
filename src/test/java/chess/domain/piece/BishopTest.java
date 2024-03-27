package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

class BishopTest {

    @ParameterizedTest
    @CsvSource(value = {"F, 6", "B, 2", "F, 2", "B, 6"})
    @DisplayName("비숍은 대각선 방향으로 이동할 수 있다")
    void bishopMoveTest(String file, int rank) {
        // given
        Bishop bishop = new Bishop(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        // when
        boolean movable = bishop.isMovable(source, Position.of(File.from(file), Rank.from(rank)));
        // then
        assertThat(movable).isTrue();
    }

    @Test
    @DisplayName("비숍은 한 번에 여러 칸 이동할 수 있다.")
    void bishopMaxUnitTest() {
        // given
        Bishop bishop = new Bishop(Color.WHITE);
        Position source = Position.of(File.A, Rank.ONE);
        // when
        boolean movable = bishop.isMovable(source, Position.of(File.H, Rank.EIGHT));
        // then
        assertThat(movable).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Direction.class, mode = Mode.MATCH_ANY, names = {".*SAME.*"})
    @DisplayName("비숍이 움직일 수 없는 경우를 판단한다.")
    void bishopInvalidMoveTest(Direction direction) {
        // given
        Bishop bishop = new Bishop(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        Position destination = direction.nextPosition(source);
        // when
        boolean movable = bishop.isMovable(source, destination);
        // then
        assertThat(movable).isFalse();
    }
}
