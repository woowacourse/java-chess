package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.position.UnitMovement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {

    @ParameterizedTest
    @CsvSource(value = {"1,0", "0,1", "-1,0", "0,-1"})
    @DisplayName("룩은 상하좌우 방향으로 이동할 수 있다.")
    void rookMoveTest(int fileDifference, int rankDifference) {
        // given
        Rook rook = new Rook(Color.WHITE);
        UnitMovement movement = UnitMovement.differencesOf(fileDifference, rankDifference);
        // when
        boolean actual = rook.isMovable(movement, 7);
        // then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1", "1,-1"})
    @DisplayName("룩이 이동할 수 없는 경우를 판단한다.")
    void rookInvalidMoveTest(int fileDifference, int rankDifference) {
        // given
        Rook rook = new Rook(Color.WHITE);
        UnitMovement movement = UnitMovement.differencesOf(fileDifference, rankDifference);
        // when
        boolean actual = rook.isMovable(movement, 1);
        // then
        assertThat(actual).isFalse();
    }
}
