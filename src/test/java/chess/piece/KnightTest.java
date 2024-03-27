package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.position.UnitMovement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    @ParameterizedTest
    @CsvSource(value = {"1,2", "1,-2", "2,1", "2,-1", "-1,2", "-1,-2", "-2,-1", "-2,1"})
    @DisplayName("나이트는 두 칸 전진한 뒤, 전진한 방향의 90도 좌/우 한 칸으로 이동할 수 있다")
    void knightMoveTest(int fileDifference, int rankDifference) {
        // given
        Knight knight = new Knight(Color.WHITE);
        UnitMovement movement = UnitMovement.differencesOf(fileDifference, rankDifference);
        // when
        boolean actual = knight.isMovable(movement, 1);
        // then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1", "1,-1", "1,0", "0,1"})
    @DisplayName("나이트가 이동할 수 없는 경우를 판단한다.")
    void knightInvalidMoveTest(int fileDifference, int rankDifference) {
        // given
        Knight knight = new Knight(Color.WHITE);
        UnitMovement unitMovement = UnitMovement.differencesOf(fileDifference, rankDifference);
        // when
        boolean actual = knight.isMovable(unitMovement, 1);
        // then
        assertThat(actual).isFalse();
    }
}
