package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.Team;
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
        boolean isAttack = false;

        assertAll(
                () -> assertThat(pawn.findPath(start, new Position(File.F, Rank.SIX), isAttack))
                        .containsExactly(new Position(File.F, Rank.SIX)),
                () -> assertThat(pawn.findPath(start, new Position(File.F, Rank.FIVE), isAttack))
                        .containsExactly(new Position(File.F, Rank.SIX), new Position(File.F, Rank.FIVE))
        );
    }

    @Test
    @DisplayName("검정 폰이 초기 위치가 아닌 곳에 있을 경우, 남쪽으로 한 칸 이동한다.")
    void findPathTest_whenBlackPawnNotInitPosition() {
        Pawn pawn = new Pawn(Team.BLACK);
        Position start = new Position(File.F, Rank.SIX);
        boolean isAttack = false;

        assertThat(pawn.findPath(start, new Position(File.F, Rank.FIVE), isAttack))
                .containsExactly(new Position(File.F, Rank.FIVE));
    }

    @Test
    @DisplayName("검정 폰에 남쪽 대각선에 상대 말이 있을 경우, 해당 말 위치로 움직일 수 있다.")
    void findPathTest_whenBlackPawnCanMoveDiagonal() {
        Pawn pawn = new Pawn(Team.BLACK);
        Position start = new Position(File.F, Rank.SIX);
        boolean isAttack = true;

        assertThat(pawn.findPath(start, new Position(File.E, Rank.FIVE), isAttack))
                .containsExactly(new Position(File.E, Rank.FIVE));
    }

    @Test
    @DisplayName("흰 폰이 초기 위치에 있을 경우, 북쪽으로 한 칸 또는 두 칸 이동한다.")
    void findPathTest_whenWhitePawnInitPosition() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position start = new Position(File.F, Rank.TWO);
        boolean isAttack = false;

        assertAll(
                () -> assertThat(pawn.findPath(start, new Position(File.F, Rank.THREE), isAttack))
                        .containsExactly(new Position(File.F, Rank.THREE)),
                () -> assertThat(pawn.findPath(start, new Position(File.F, Rank.FOUR), isAttack))
                        .containsExactly(new Position(File.F, Rank.THREE), new Position(File.F, Rank.FOUR))
        );
    }

    @Test
    @DisplayName("흰 폰이 초기 위치가 아닌 곳에 있을 경우, 북쪽으로 한 칸 이동한다.")
    void findPathTest_whenWhitePawnNotInitPosition() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position start = new Position(File.F, Rank.FOUR);
        boolean isAttack = false;

        assertThat(pawn.findPath(start, new Position(File.F, Rank.FIVE), isAttack))
                .containsExactly(new Position(File.F, Rank.FIVE));
    }

    @Test
    @DisplayName("흰 폰에 북쪽 대각선에 상대 말이 있을 경우, 해당 말 위치로 움직일 수 있다.")
    void findPathTest_whenWhitePawnCanMoveDiagonal() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position start = new Position(File.F, Rank.FOUR);
        boolean isAttack = true;

        assertThat(pawn.findPath(start, new Position(File.E, Rank.FIVE), isAttack))
                .containsExactly(new Position(File.E, Rank.FIVE));
    }

    @ParameterizedTest
    @CsvSource({"G, FOUR", "F, FIVE", "F, TWO", "E, FOUR", "G, FOUR"})
    @DisplayName("적이 없을 때 이동할 수 없는 곳인 경우, 예외가 발생한다.")
    void findPathTest_whenOutOfMovement_throwException(File file, Rank rank) {
        Pawn pawn = new Pawn(Team.BLACK);
        Position start = new Position(File.F, Rank.FOUR);
        Position end = new Position(file, rank);
        boolean isAttack = false;

        assertThatThrownBy(() -> pawn.findPath(start, end, isAttack))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("불가능한 경로입니다.");
    }

    @ParameterizedTest
    @CsvSource({"G, FOUR", "F,FIVE", "F, THREE", "F, TWO"})
    @DisplayName("적이 있을 때 이동할 수 없는 곳인 경우, 예외가 발생한다.")
    void findPathTest_whenOutOfDiagonalMovement_throwException(File file, Rank rank) {
        Pawn pawn = new Pawn(Team.BLACK);
        Position start = new Position(File.F, Rank.FOUR);
        Position end = new Position(file, rank);
        boolean isAttack = true;

        assertThatThrownBy(() -> pawn.findPath(start, end, isAttack))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("불가능한 경로입니다.");
    }
}
