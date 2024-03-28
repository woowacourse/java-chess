package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.position.UnitMovement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @ParameterizedTest
    @CsvSource(value = {"1,1", "1,0", "1,-1", "0,1", "0,-1", "-1,-1", "-1,0", "-1,1"})
    @DisplayName("킹은 상하좌우 및 대각선 방향으로 한 칸 이동할 수 있다.")
    void kingMoveTest(int fileDifference, int rankDifference) {
        // given
        King king = new King(Color.WHITE);
        UnitMovement movement = UnitMovement.differencesOf(fileDifference, rankDifference);
        // when
        boolean actual = king.isMovable(movement, 1);
        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("킹은 한 번에 여러 칸 이동할 수 없다.")
    void kingMaxUnitTest() {
        // given
        King king = new King(Color.WHITE);
        UnitMovement movement = UnitMovement.differencesOf(1, 1);
        // when
        boolean actual = king.isMovable(movement, 2);
        // then
        assertThat(actual).isFalse();
    }
}
