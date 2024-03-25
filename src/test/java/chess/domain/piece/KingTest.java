package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @ParameterizedTest
    @CsvSource(value = {"D, 5", "D, 3", "E, 5", "E, 3", "E, 4", "C, 4", "C, 5", "C, 3"})
    @DisplayName("킹은 상하좌우 및 대각선 방향으로 한 칸 이동할 수 있다.")
    void kingMoveTest(String file, int rank) {
        // given
        King king = new King(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        // when
        boolean movable = king.isMovable(source, Position.of(File.from(file), Rank.from(rank)));
        // then
        assertThat(movable).isTrue();
    }

    @Test
    @DisplayName("킹은 한 번에 여러 칸 이동할 수 없다.")
    void kingMaxUnitTest() {
        // given
        King king = new King(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        // when,
        boolean movable = king.isMovable(source, Position.of(File.D, Rank.SIX));
        // then
        assertThat(movable).isFalse();
    }
}
