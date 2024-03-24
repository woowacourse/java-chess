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

class BishopTest {

    @Test
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest() {
        Bishop bishop = new Bishop(Team.WHITE);
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(File.A, Rank.ONE);
        boolean isAttack = false;

        assertThat(bishop.findPath(start, end, isAttack))
                .containsExactly(
                        new Position(File.C, Rank.THREE),
                        new Position(File.B, Rank.TWO),
                        new Position(File.A, Rank.ONE));
    }

    @ParameterizedTest
    @CsvSource({"E, FIVE", "F, SIX", "G, ONE", "B, TWO", "E, THREE", "G, SEVEN", "A, ONE", "C, THREE"})
    @DisplayName("이동이 가능한 경우, 예외를 발생하지 않는다.")
    void findPathTest_whenCanMove(File file, Rank rank) {
        Bishop bishop = new Bishop(Team.WHITE);
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(file, rank);
        boolean isAttack = false;

        assertThatCode(() -> bishop.findPath(start, end, isAttack)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource({"D, TWO", "F, FOUR", "C, TWO", "E, SIX"})
    @DisplayName("이동할 수 없는 곳인 경우, 예외가 발생한다.")
    void findPathTest_whenOutOfMovement_throwException(File file, Rank rank) {
        Bishop bishop = new Bishop(Team.WHITE);
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(file, rank);
        boolean isAttack = false;

        assertThatThrownBy(() -> bishop.findPath(start, end, isAttack))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("불가능한 경로입니다.");
    }
}
