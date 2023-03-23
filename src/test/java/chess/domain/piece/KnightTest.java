package chess.domain.piece;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    private final Knight knight = new Knight(Team.BLACK);

    @ParameterizedTest(name = "나이트는 L자 형태가 아닌 방향으로 이동하면 예외가 발생한다.")
    @CsvSource({"0,2,false", "3,0,false", "2,2,true", "-2,-2,false", "-3,3,false", "2,-2,false"})
    void canMove_byLShape_fail(int fileInterval, int rankInterval, boolean canAttack) {
        Assertions.assertThatThrownBy(
                        () -> knight.canMove(fileInterval, rankInterval, canAttack))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("나이트가 이동할 수 없는 방향입니다.");
    }

    @ParameterizedTest(name = "나이트는 L자 형태로 3칸을 이동할 수 있는 곳으로 움직일 수 있다.")
    @CsvSource({"-2,1,true", "-2,-1,true","1,2,true", "-1,2,false",
            "-1,-2,true", "1,-2,true", "2,1,true", "2,-1,true"})
    void canMove_success(int fileInterval, int rankInterval, boolean canAttack) {
        assertDoesNotThrow(() -> knight.canMove(fileInterval, rankInterval, canAttack));
    }
}
