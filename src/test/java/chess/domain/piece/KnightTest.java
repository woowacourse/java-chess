package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    @ParameterizedTest
    @CsvSource({"G, SIX", "H, FIVE", "H, THREE", "G, TWO", "E, TWO", "D, THREE", "D, FIVE", "E, SIX"})
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest(File file, Rank rank) {
        Knight knight = new Knight(Team.BLACK);
        Position start = new Position(File.F, Rank.FOUR);
        Position end = new Position(file, rank);
        boolean isAttack = false;

        assertThat(knight.findPath(start, end, isAttack)).containsExactly(end);
    }

    @ParameterizedTest
    @CsvSource({"G, FIVE", "H, FOUR", "D, TWO", "E, ONE"})
    @DisplayName("이동할 수 없는 곳인 경우, 예외가 발생한다.")
    void findPathTest_whenOutOfMovement_throwException(File file, Rank rank) {
        Knight knight = new Knight(Team.BLACK);
        Position start = new Position(File.F, Rank.FOUR);
        Position end = new Position(file, rank);
        boolean isAttack = false;

        assertThatThrownBy(() -> knight.findPath(start, end, isAttack))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("불가능한 경로입니다.");
    }
}
