package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    private static final boolean HAS_ENEMY = false;

    @ParameterizedTest
    @CsvSource({"F, THREE", "F, FIVE", "E, THREE", "E, FOUR", "E, FIVE", "G, THREE", "G, FOUR", "G, FIVE"})
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest(File file, Rank rank) {
        King king = new King(Team.BLACK);
        Position start = new Position(File.F, Rank.FOUR);
        Position end = new Position(file, rank);

        assertThat(king.findPath(start, end, HAS_ENEMY))
                .isEmpty();
    }

    @ParameterizedTest
    @CsvSource({"F, TWO", "F, SIX", "D, THREE", "H, FOUR"})
    @DisplayName("이동할 수 없는 곳인 경우, 예외가 발생한다.")
    void findPathTest_whenOutOfMovement_throwException(File file, Rank rank) {
        King king = new King(Team.BLACK);
        Position start = new Position(File.F, Rank.FOUR);
        Position end = new Position(file, rank);

        assertThatThrownBy(() -> king.findPath(start, end, HAS_ENEMY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("불가능한 경로입니다.");
    }
}
