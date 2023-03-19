package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {
    private final Queen queen = new Queen(Team.BLACK);

    @ParameterizedTest(name = "퀸은 45도 각도의 대각선 또는 상하좌우 방향이 아닌 칸으로 움직이면 예외가 발생한다.")
    @CsvSource({"1,2,false", "2,1,false", "3,1,true", "-3,-1,false", "1,-2,false", "-3,-2,false"})
    void canMove_fail(int fileInterval, int rankInterval, boolean canAttack) {
        Assertions.assertThatThrownBy(
                        () -> queen.canMove(fileInterval, rankInterval, canAttack))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "퀸은 모든 방향으로 이동할 수 있다.")
    @CsvSource({"1,1,true", "-1,1,true","1,-1,true", "-1,-1,false",
            "2,2,true", "-3,3,true","4,-4,true", "-5,-5,false",
            "0,1,true", "0,-1,true", "-1,0,true", "1,0,true",
            "0,5,true", "0,-4,true", "-3,0,true", "2,0,true"})
    void canMove_success(int fileInterval, int rankInterval, boolean canAttack) {
        assertDoesNotThrow(() -> queen.canMove(fileInterval, rankInterval, canAttack));
    }
}
