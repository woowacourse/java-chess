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

class RookTest {

    @Test
    @DisplayName("룩은 상하좌우 방향으로 이동할 수 있다.")
    void rookMoveTest() {
        // given
        Rook rook = new Rook(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        // when, then
        assertAll(
                () -> assertThat(rook.isMovable(source, Position.of(File.D, Rank.EIGHT))).isTrue(),
                () -> assertThat(rook.isMovable(source, Position.of(File.D, Rank.ONE))).isTrue(),
                () -> assertThat(rook.isMovable(source, Position.of(File.A, Rank.FOUR))).isTrue(),
                () -> assertThat(rook.isMovable(source, Position.of(File.H, Rank.FOUR))).isTrue()
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1", "1,-1"})
    @DisplayName("룩이 이동할 수 없는 경우를 판단한다.")
    void rookInvalidMoveTest(int fileDifference, int rankDifference) {
        // given
        Rook rook = new Rook(Color.WHITE);
        Position source = Position.of(File.D, Rank.FOUR);
        UnitDirection unitDirection = UnitDirection.differencesOf(fileDifference, rankDifference);
        Position destination = unitDirection.nextPosition(source);
        // when, then
        assertThat(rook.isMovable(source, destination)).isFalse();
    }
}
