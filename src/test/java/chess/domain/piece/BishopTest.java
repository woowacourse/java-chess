package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

    private final Bishop bishop = new Bishop(Team.BLACK);

    @ParameterizedTest(name = "비숍은 대각선 방향이 아닌 곳으로 이동하면 예외가 발생한다.")
    @CsvSource({"1,0,true", "-3,0,true","0,-4,true", "0,7,false",
            "0,1,true", "0,-1,true", "-1,0,true", "1,0,true"})
    void canMove_fail(int fileInterval, int rankInterval, boolean canAttack) {
        Assertions.assertThatThrownBy(
                        () -> bishop.canMove(fileInterval, rankInterval, canAttack))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비숍은 대각선으로만 이동할 수 있습니다.");
    }

    @ParameterizedTest(name = "비숍은 대각선으로 이동할 수 있다.")
    @CsvSource({"2,2,false", "3,3,false", "-4,-4,false", "-7,7,false", "1,-1,false"})
    void canMove_success(int fileInterval, int rankInterval, boolean canAttack) {
        assertDoesNotThrow(() -> bishop.canMove(fileInterval, rankInterval, canAttack));
    }
}
