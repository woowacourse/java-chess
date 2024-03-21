package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    @Test
    @DisplayName("검정 폰이 초기 위치에 있을 경우, 남쪽으로 한 칸 또는 두 칸 이동한다.")
    void findPathTest_whenBlackPawnInitPosition() {
        Pawn pawn = new Pawn(Team.BLACK);
        Position start = new Position(File.F, Rank.SEVEN);

        assertAll(
                () -> assertThat(pawn.findPath(start, new Position(File.F, Rank.SIX)))
                        .containsExactly(new Position(File.F, Rank.SIX)),
                () -> assertThat(pawn.findPath(start, new Position(File.F, Rank.FIVE)))
                        .containsExactly(new Position(File.F, Rank.SIX), new Position(File.F, Rank.FIVE))
        );
    }

    @Test
    @DisplayName("검정 폰이 초기 위치가 아닌 곳에 있을 경우, 남쪽으로 한 칸 이동한다.")
    void findPathTest_whenBlackPawnNotInitPosition() {
        Pawn pawn = new Pawn(Team.BLACK);
        Position start = new Position(File.F, Rank.SIX);

        assertThat(pawn.findPath(start, new Position(File.F, Rank.FIVE)))
                .containsExactly(new Position(File.F, Rank.FIVE));
    }

    @Test
    @DisplayName("흰 폰이 초기 위치에 있을 경우, 북쪽으로 한 칸 또는 두 칸 이동한다.")
    void findPathTest_whenWhitePawnInitPosition() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position start = new Position(File.F, Rank.TWO);

        assertAll(
                () -> assertThat(pawn.findPath(start, new Position(File.F, Rank.THREE)))
                        .containsExactly(new Position(File.F, Rank.THREE)),
                () -> assertThat(pawn.findPath(start, new Position(File.F, Rank.FOUR)))
                        .containsExactly(new Position(File.F, Rank.THREE), new Position(File.F, Rank.FOUR))
        );
    }

    @Test
    @DisplayName("흰 폰이 초기 위치가 아닌 곳에 있을 경우, 북쪽으로 한 칸 이동한다.")
    void findPathTest_whenWhitePawnNotInitPosition() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position start = new Position(File.F, Rank.FOUR);

        assertThat(pawn.findPath(start, new Position(File.F, Rank.FIVE)))
                .containsExactly(new Position(File.F, Rank.FIVE));
    }

    @ParameterizedTest
    @CsvSource({"G, FOUR", "F, FIVE", "F, TWO"})
    @DisplayName("이동할 수 없는 곳인 경우, 예외가 발생한다.")
    void findPathTest_whenOutOfMovement_throwException(File file, Rank rank) {
        Pawn pawn = new Pawn(Team.BLACK);
        Position start = new Position(File.F, Rank.FOUR);
        Position end = new Position(file, rank);

        assertThatThrownBy(() -> pawn.findPath(start, end))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("불가능한 경로입니다.");
    }
}
