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
    @DisplayName("검정 폰이 초기 위치에 있고 목적지에 적이 없을 경우, 남쪽으로 한 칸 또는 두 칸 이동한다.")
    void findPathTest_whenBlackPawnInitPosition() {
        Pawn pawn = new Pawn(Team.BLACK);
        Position start = new Position(File.F, Rank.SEVEN);
        boolean hasEnemy = false;

        assertAll(
                () -> assertThat(pawn.findPath(start, new Position(File.F, Rank.SIX), hasEnemy))
                        .containsExactly(new Position(File.F, Rank.SIX)),
                () -> assertThat(pawn.findPath(start, new Position(File.F, Rank.FIVE), hasEnemy))
                        .containsExactly(new Position(File.F, Rank.SIX), new Position(File.F, Rank.FIVE))
        );
    }

    @Test
    @DisplayName("검정 폰이 초기 위치가 아닌 곳에 있고 목적지에 적이 없을 경우, 남쪽으로 한 칸 이동한다.")
    void findPathTest_whenBlackPawnNotInitPosition() {
        Pawn pawn = new Pawn(Team.BLACK);
        Position start = new Position(File.F, Rank.SIX);
        boolean hasEnemy = false;

        assertThat(pawn.findPath(start, new Position(File.F, Rank.FIVE), hasEnemy))
                .containsExactly(new Position(File.F, Rank.FIVE));
    }

    @Test
    @DisplayName("검정 폰의 목적지에 적이 있을 경우, 남쪽으로 이동할 수 없다.")
    void findPathTest_whenMoveToSouthExistEnemy_throwException() {
        Pawn pawn = new Pawn(Team.BLACK);
        Position start = new Position(File.F, Rank.FOUR);
        boolean hasEnemy = true;

        assertThatThrownBy(() -> pawn.findPath(start, start.moveToSouth(), hasEnemy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("불가능한 경로입니다.");
    }

    @Test
    @DisplayName("검정 폰이 목적지에 적이 있을 경우, 남동쪽 혹은 남서쪽으로 한 칸 이동한다.")
    void findPathTest_whenBlackPawnHaveEnemy() {
        Pawn pawn = new Pawn(Team.BLACK);
        Position start = new Position(File.F, Rank.FOUR);
        boolean hasEnemy = true;

        assertAll(
                () -> assertThat(pawn.findPath(start, new Position(File.E, Rank.THREE), hasEnemy))
                        .containsExactly(new Position(File.E, Rank.THREE)),
                () -> assertThat(pawn.findPath(start, new Position(File.G, Rank.THREE), hasEnemy))
                        .containsExactly(new Position(File.G, Rank.THREE))
        );
    }

    @Test
    @DisplayName("검정 폰이 목적지에 적이 없을 경우, 남동쪽 혹은 남서쪽으로 한 칸 이동할 수 없다.")
    void findPathTest_whenBlackPawnMoveDiagonalNotExistEnemy() {
        Pawn pawn = new Pawn(Team.BLACK);
        Position start = new Position(File.F, Rank.FOUR);
        boolean hasEnemy = false;

        assertAll(
                () -> assertThatThrownBy(() -> pawn.findPath(start, new Position(File.E, Rank.THREE), hasEnemy))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("불가능한 경로입니다."),
                () -> assertThatThrownBy(() -> pawn.findPath(start, new Position(File.G, Rank.THREE), hasEnemy))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("불가능한 경로입니다.")
        );
    }

    @Test
    @DisplayName("흰 폰이 초기 위치에 있고 목적지에 적이 없을 경우, 북쪽으로 한 칸 또는 두 칸 이동한다.")
    void findPathTest_whenWhitePawnInitPosition() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position start = new Position(File.F, Rank.TWO);
        boolean hasEnemy = false;

        assertAll(
                () -> assertThat(pawn.findPath(start, new Position(File.F, Rank.THREE), hasEnemy))
                        .containsExactly(new Position(File.F, Rank.THREE)),
                () -> assertThat(pawn.findPath(start, new Position(File.F, Rank.FOUR), hasEnemy))
                        .containsExactly(new Position(File.F, Rank.THREE), new Position(File.F, Rank.FOUR))
        );
    }

    @Test
    @DisplayName("흰 폰이 초기 위치가 아닌 곳에 있고 목적지에 적이 없을 경우, 북쪽으로 한 칸 이동한다.")
    void findPathTest_whenWhitePawnNotInitPosition() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position start = new Position(File.F, Rank.FOUR);
        boolean hasEnemy = false;

        assertThat(pawn.findPath(start, new Position(File.F, Rank.FIVE), hasEnemy))
                .containsExactly(new Position(File.F, Rank.FIVE));
    }

    @Test
    @DisplayName("흰 폰의 목적지에 적이 있을 경우, 북쪽으로 이동할 수 없다.")
    void findPathTest_whenWhitePawnMoveToSouthExistEnemy_throwException() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position start = new Position(File.F, Rank.FOUR);
        boolean hasEnemy = true;

        assertThatThrownBy(() -> pawn.findPath(start, start.moveToNorth(), hasEnemy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("불가능한 경로입니다.");
    }

    @Test
    @DisplayName("흰 폰이 목적지에 적이 있을 경우, 북동쪽 혹은 북서쪽으로 한 칸 이동한다.")
    void findPathTest_whenWhitePawnHaveEnemy() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position start = new Position(File.F, Rank.FOUR);
        boolean hasEnemy = true;

        assertAll(
                () -> assertThat(pawn.findPath(start, new Position(File.E, Rank.FIVE), hasEnemy))
                        .containsExactly(new Position(File.E, Rank.FIVE)),
                () -> assertThat(pawn.findPath(start, new Position(File.G, Rank.FIVE), hasEnemy))
                        .containsExactly(new Position(File.G, Rank.FIVE))
        );
    }

    @Test
    @DisplayName("흰 폰이 목적지에 적이 없을 경우, 북동쪽 혹은 북서쪽으로 한 칸 이동할 수 없다.")
    void findPathTest_whenWhitePawnMoveDiagonalNotExistEnemy() {
        Pawn pawn = new Pawn(Team.WHITE);
        Position start = new Position(File.F, Rank.FOUR);
        boolean hasEnemy = false;

        assertAll(
                () -> assertThatThrownBy(() -> pawn.findPath(start, new Position(File.E, Rank.FIVE), hasEnemy))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("불가능한 경로입니다."),
                () -> assertThatThrownBy(() -> pawn.findPath(start, new Position(File.G, Rank.FIVE), hasEnemy))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("불가능한 경로입니다.")
        );
    }

    @ParameterizedTest
    @CsvSource({"G, FOUR", "F, FIVE", "F, TWO"})
    @DisplayName("이동할 수 없는 곳인 경우, 예외가 발생한다.")
    void findPathTest_whenOutOfMovement_throwException(File file, Rank rank) {
        Pawn pawn = new Pawn(Team.BLACK);
        Position start = new Position(File.F, Rank.FOUR);
        Position end = new Position(file, rank);
        boolean hasEnemy = false;

        assertThatThrownBy(() -> pawn.findPath(start, end, hasEnemy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("불가능한 경로입니다.");
    }
}
