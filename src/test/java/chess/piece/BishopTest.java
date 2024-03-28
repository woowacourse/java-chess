package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.position.UnitMovement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

    @ParameterizedTest
    @CsvSource(value = {"1,1", "-1,-1", "1,-1", "-1,1"})
    @DisplayName("비숍은 대각선 방향으로 이동할 수 있다")
    void bishopMoveTest(int fileDifference, int rankDifference) {
        // given
        Bishop bishop = new Bishop(Color.WHITE);
        UnitMovement movement = UnitMovement.differencesOf(fileDifference, rankDifference);
        // when
        boolean actual = bishop.isMovable(movement, 7);
        // then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1,0", "0,-1"})
    @DisplayName("비숍이 움직일 수 없는 경우를 판단한다.")
    void bishopInvalidMoveTest(int fileDifference, int rankDifference) {
        // given
        Bishop bishop = new Bishop(Color.WHITE);
        UnitMovement unitMovement = UnitMovement.differencesOf(fileDifference, rankDifference);
        // when
        boolean actual = bishop.isMovable(unitMovement, 1);
        // then
        assertThat(actual).isFalse();
    }
}
