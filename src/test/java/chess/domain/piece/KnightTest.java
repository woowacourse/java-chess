package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    @ParameterizedTest
    @CsvSource(value = {"F, 5", "B, 5", "F, 3", "B, 3", "E, 6", "C, 6", "E, 2", "C, 2"})
    @DisplayName("나이트는 두 칸 전진한 뒤, 전진한 방향의 90도 좌/우 한 칸으로 이동할 수 있다")
    void knightMoveTest() {
        // given
        Knight knight = new Knight(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        // when
        boolean movable = knight.isMovable(source, Position.of(File.F, Rank.FIVE));
        // then
        assertThat(movable).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"D, 5", "D, 3", "E, 4", "C, 4", "C, 5", "C, 3", "E, 5", "E, 3"})
    @DisplayName("나이트는 기본적인 8방향으로 움직일 수 없다.")
    void knightInvalidMoveTest(String file, int rank) {
        // given
        Knight knight = new Knight(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        // when
        boolean movable = knight.isMovable(source, Position.of(File.from(file), Rank.from(rank)));
        // then
        assertThat(movable).isFalse();
    }
}
