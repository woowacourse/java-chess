package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {

    @Test
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest() {
        Rook rook = new Rook(Team.BLACK);
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(File.A, Rank.FOUR);
        boolean isAttack = false;

        assertThat(rook.findPath(start, end, isAttack))
                .containsExactly(
                        new Position(File.C, Rank.FOUR),
                        new Position(File.B, Rank.FOUR),
                        new Position(File.A, Rank.FOUR));
    }

    @ParameterizedTest
    @CsvSource({"D, FIVE", "D, SIX", "D, ONE", "D, TWO", "E, FOUR", "G, FOUR", "A, FOUR", "C, FOUR"})
    @DisplayName("이동이 가능한 경우, 예외를 발생하지 않는다.")
    void findPathTest_whenCanMove(File file, Rank rank) {
        Rook rook = new Rook(Team.BLACK);
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(file, rank);
        boolean isAttack = false;

        assertThatCode(() -> rook.findPath(start, end, isAttack)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource({"F, TWO", "F, SIX", "C, THREE", "E, FIVE"})
    @DisplayName("이동할 수 없는 곳인 경우, 예외가 발생한다.")
    void findPathTest_whenOutOfMovement_throwException(File file, Rank rank) {
        Rook rook = new Rook(Team.WHITE);
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(file, rank);
        boolean isAttack = false;

        assertThatThrownBy(() -> rook.findPath(start, end, isAttack))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("불가능한 경로입니다.");
    }
}
