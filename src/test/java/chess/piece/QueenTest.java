package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.position.UnitDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

    @ParameterizedTest
    @CsvSource(value = {"1,-1", "1,0", "1,1", "0,-1", "0,1", "-1,-1", "-1,0", "-1,1"})
    @DisplayName("퀸은 상하좌우 및 대각선 방향으로 이동할 수 있다.")
    void queenMoveTest(int fileDiffence, int rankDifference) {
        // given
        Queen queen = new Queen(Color.WHITE);
        UnitDirection direction = UnitDirection.differencesOf(fileDiffence, rankDifference);
        // when
        boolean actual = queen.isMovable(direction, 7);
        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("퀸이 이동할 수 없는 경우를 판단한다.")
    void queenInvalidMoveTest() {
        // given
        Queen queen = new Queen(Color.WHITE);
        UnitDirection direction = UnitDirection.differencesOf(1, 2);
        // when
        boolean actual = queen.isMovable(direction, 1);
        // then
        assertThat(actual).isFalse();
    }
}
