package chess.domain.piece;

import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.grid.gridStrategy.TestGridStrategy;
import chess.domain.position.Position;
import chess.exception.ChessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PawnTest {
    @Test
    @DisplayName("Pawn을 정상적으로 생성하는 지 테스트")
    public void init() {
        assertThatCode(() -> {
            new Pawn(Color.BLACK, 'a', '2');
            new Pawn(Color.BLACK, 'a', 2);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Pawn의 서로 다른 생성자에 따라서, 올바르게 포지션이 들어가는 지 테스트")
    public void init_isSamePosition() {
        Piece pawn = new Pawn(Color.BLACK, 'a', '2');
        Piece pawn2 = new Pawn(Color.BLACK, 'a', 2);
        Position expectedPosition = new Position("a2");
        assertThat(pawn.position()).isEqualTo(expectedPosition);
        assertThat(pawn2.position()).isEqualTo(expectedPosition);
    }

    @Test
    @DisplayName("Pawn이 빈 공간으로 한 칸 전진할 수 있는 지 테스트")
    public void validateMove_GoOneStep() {
        Grid grid = new Grid(new TestGridStrategy());
        Pawn pawn = new Pawn(Color.WHITE, 'b', '2');
        grid.lines().assign(new Position('b', '2'), pawn);
        assertThatCode(() -> {
            pawn.validateSteps(new Empty('b', '3'), grid.lines());
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Pawn이 뒤로 못가는 지 검증")
    public void validateMove_Back() {
        Grid grid = new Grid(new TestGridStrategy());
        Pawn pawn = new Pawn(Color.WHITE, 'b', '2');
        grid.lines().assign(new Position("b2"), pawn);
        assertThatThrownBy(() -> {
            pawn.validateSteps(new Empty('b', '1'), grid.lines());
        }).isInstanceOf(ChessException.class).hasMessage("이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("Pawn이 처음 움직일 때만 2칸 앞으로 전진할 수 있는 지 테스트")
    public void validateMove_GoTwoStep() {
        Grid grid = new Grid(new NormalGridStrategy());
        Pawn pawn = new Pawn(Color.WHITE, 'b', '2');
        assertThatCode(() -> {
            pawn.validateSteps(new Empty('b', '4'), grid.lines());
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Pawn이 움직인 적이 있을 때 앞으로 전진하면 예외 발생하는 지 테스트")
    public void validateMove_GoTwoStep_ThrowException() {
        Grid grid = new Grid(new TestGridStrategy());
        Pawn pawn = new Pawn(Color.WHITE, 'b', '2');
        grid.lines().assign(new Position("b2"), pawn);
        grid.move(pawn, new Empty('b', '3'));
        assertThatThrownBy(() -> {
            pawn.validateSteps(new Empty('b', '5'), grid.lines());
        }).isInstanceOf(ChessException.class).hasMessage("폰은 초기 자리에서만 두칸 이동 가능합니다.");
    }

    @Test
    @DisplayName("Pawn이 빈 공간으로 이동할 때에는 대각선으로 이동할 수 없다.")
    public void validateMove_DiagonalStep_ThrowException() {
        Grid grid = new Grid(new TestGridStrategy());
        Pawn pawn = new Pawn(Color.WHITE, 'b', '2');
        grid.lines().assign(new Position("b2"), pawn);
        assertThatThrownBy(() -> {
            pawn.validateSteps(new Empty('c', '3'), grid.lines());
        }).isInstanceOf(ChessException.class).hasMessage("폰은 상대 말을 먹을 때만 대각선으로 이동이 가능합니다.");
    }

    @Test
    @DisplayName("Pawn 앞에 말이 있으면 앞으로 이동할 수 없다.")
    public void validateMove_Obstacle_ThrowException() {
        Grid grid = new Grid(new TestGridStrategy());
        Pawn pawn = new Pawn(Color.WHITE, 'b', '2');
        Pawn obstaacle = new Pawn(Color.BLACK, 'b', '3');
        grid.lines().assign(new Position("b2"), pawn);
        grid.lines().assign(new Position("b3"), obstaacle);
        assertThatThrownBy(() -> {
            pawn.validateSteps(obstaacle, grid.lines());
        }).isInstanceOf(ChessException.class).hasMessage("폰은 한칸 앞 말이 있으면 가지 못합니다.");
    }
}
