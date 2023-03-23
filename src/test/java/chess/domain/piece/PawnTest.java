package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {
    private final Pawn blackPawn = new Pawn(Team.BLACK);
    private final Pawn whitePawn = new Pawn(Team.WHITE);

    @ParameterizedTest(name = "폰은 한 칸 이상 움직이면 예외가 발생한다.")
    @CsvSource({"0,2,false", "0,3,false", "0,4,true"})
    void canMove_byDisContinuous_fail(int fileInterval, int rankInterval, boolean canAttack) {
        Assertions.assertThatThrownBy(
                        () -> whitePawn.canMove(fileInterval, rankInterval, canAttack))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 첫 이동이 아니면 조건에 따라 대각선 또는 앞으로 한 칸만 이동 가능합니다.");
    }

    @ParameterizedTest(name = "폰은 좌우로 움직이면 예외가 발생한다.")
    @CsvSource({"1,0,false", "-1,0,false", "1,0,true"})
    void canMove_byVertical_fail(int fileInterval, int rankInterval, boolean canAttack) {
        Assertions.assertThatThrownBy(
                        () -> whitePawn.canMove(fileInterval, rankInterval, canAttack))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 좌우로 움직일 수 없습니다.");
    }

    @ParameterizedTest(name = "폰은 대각선에 적이 위치하지 않을 때 대각선으로 이동하면 예외가 발생한다.")
    @CsvSource({"1,1,false", "-1,1,false"})
    void canMove_byDiagonal_fail(int fileInterval, int rankInterval, boolean canAttack) {
        Assertions.assertThatThrownBy(
                        () -> whitePawn.canMove(fileInterval, rankInterval, canAttack))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 첫 이동이나 대각선에 적이 위치하지 않는다면 앞으로 한 칸만 이동 가능합니다.");
    }

    @ParameterizedTest(name = "화이트 폰은 대각선에 적이 위치하면 앞쪽 대각선으로 한 칸, 아니면 앞으로 한 칸 이동한다.")
    @CsvSource({"1,1,true", "-1,1,true","0,1,true"})
    void whitePawn_canMove_success(int fileInterval, int rankInterval, boolean canAttack) {
        assertDoesNotThrow(() -> whitePawn.canMove(fileInterval, rankInterval, canAttack));
    }

    @ParameterizedTest(name = "블랙 폰은 대각선에 적이 위치하면 앞쪽 대각선으로 한 칸, 아니면 앞으로 한 칸 이동한다.")
    @CsvSource({"1,-1,true", "-1,-1,true", "0,-1,true"})
    void blackPawn_canMove_success(int fileInterval, int rankInterval, boolean canAttack) {
        assertDoesNotThrow(() -> blackPawn.canMove(fileInterval, rankInterval, canAttack));
    }

    @ParameterizedTest(name = "화이트 폰은 위쪽 방향으로 움직이지 않으면 예외가 발생한다.")
    @CsvSource({"0,-1,false", "0,-1,true"})
    void whitePawn_canMove_byAdvance_fail(int fileInterval, int rankInterval, boolean canAttack) {
        Assertions.assertThatThrownBy(
                        () -> whitePawn.canMove(fileInterval, rankInterval, canAttack))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("화이트 폰은 위로만 움직일 수 있습니다.");
    }

    @ParameterizedTest(name = "블랙 폰은 아래쪽 방향으로 움직이지 않으면 예외가 발생한다.")
    @CsvSource({"0,1,false", "0,1,true"})
    void blackPawn_canMove_byAdvance_fail(int fileInterval, int rankInterval, boolean canAttack) {
        Assertions.assertThatThrownBy(
                        () -> blackPawn.canMove(fileInterval, rankInterval, canAttack))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("블랙 폰은 아래로만 움직일 수 있습니다.");
    }
}
