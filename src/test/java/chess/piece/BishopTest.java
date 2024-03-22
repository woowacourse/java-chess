package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.position.UnitDirection;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

class BishopTest {

    @Test
    @DisplayName("비숍은 대각선 방향으로 이동할 수 있다")
    void bishopMoveTest() {
        // given
        Bishop bishop = new Bishop(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        // when, then
        assertAll(
                () -> assertThat(bishop.isMovable(source, Position.of(File.F, Rank.SIX))).isTrue(),
                () -> assertThat(bishop.isMovable(source, Position.of(File.B, Rank.TWO))).isTrue(),
                () -> assertThat(bishop.isMovable(source, Position.of(File.F, Rank.TWO))).isTrue(),
                () -> assertThat(bishop.isMovable(source, Position.of(File.B, Rank.SIX))).isTrue()
        );
    }

    @Test
    @DisplayName("비숍은 한 번에 여러 칸 이동할 수 있다.")
    void bishopMaxUnitTest() {
        // given
        Bishop bishop = new Bishop(Color.WHITE);
        Position source = Position.of(File.A, Rank.ONE);
        // when, then
        assertThat(bishop.isMovable(source, Position.of(File.H, Rank.EIGHT))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1,0", "0,-1"})
    @DisplayName("비숍이 움직일 수 없는 경우를 판단한다.")
    void bishopInvalidMoveTest(int fileDifference, int rankDifference) {
        // given
        Bishop bishop = new Bishop(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        UnitDirection unitDirection = UnitDirection.differencesOf(fileDifference, rankDifference);
        Position destination = unitDirection.nextPosition(source);
        // when, then
        assertThat(bishop.isMovable(source, destination)).isFalse();
    }
}
