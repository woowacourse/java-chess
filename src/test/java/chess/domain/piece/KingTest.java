package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {
    private final King king = new King(Team.BLACK);

    @ParameterizedTest(name = "킹은 한 칸 이상 움직이면 예외가 발생한다.")
    @CsvSource({"0,2,false", "3,0,false", "2,2,true", "-2,-2,false", "-3,3,false", "2,-2,false"})
    void canMove_fail(int fileInterval, int rankInterval, boolean canAttack) {
        Assertions.assertThatThrownBy(
                        () -> king.canMove(fileInterval, rankInterval, canAttack))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 한 칸만 이동 가능합니다.");
    }

    @ParameterizedTest(name = "킹은 모든 방향으로 한 칸 이동할 수 있다.")
    @CsvSource({"1,1,true", "-1,1,true","1,-1,true", "-1,-1,false",
            "0,1,true", "0,-1,true", "-1,0,true", "1,0,true"})
    void canMove_success(int fileInterval, int rankInterval, boolean canAttack) {
        assertDoesNotThrow(() -> king.canMove(fileInterval, rankInterval, canAttack));
    }
}
