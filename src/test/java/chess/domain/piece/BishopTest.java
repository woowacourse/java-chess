package chess.domain.piece;

import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.TestGridStrategy;
import chess.domain.position.Position;
import chess.exception.ChessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BishopTest {
    @Test
    @DisplayName("Bishop 정상적으로 생성하는 지 테스트")
    public void init() {
        assertThatCode(() -> {
            new Bishop(Color.BLACK, 'a', '2');
            new Bishop(Color.BLACK, 'a', 2);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Bishop의 서로 다른 생성자에 따라서, 올바르게 포지션이 들어가는 지 테스트")
    public void init_isSamePosition() {
        Piece pawn = new Bishop(Color.BLACK, 'a', '2');
        Piece pawn2 = new Bishop(Color.BLACK, 'a', 2);
        Position expectedPosition = new Position("a2");
        assertThat(pawn.position()).isEqualTo(expectedPosition);
        assertThat(pawn2.position()).isEqualTo(expectedPosition);
    }

    @Test
    @DisplayName("Bishop이 갈 수 있는 위치, true")
    public void validateMove_True() {
        Grid grid = new Grid(new TestGridStrategy());
        Bishop bishop = new Bishop(Color.WHITE, 'b', '2');
        grid.lines().assign(new Position("b2"), bishop);
        assertThatCode(() -> {
            bishop.validateSteps(new Empty('c', '3'), grid.lines());
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Bishop이 갈 수 있는 위치: 상대말 먹기")
    public void validateRoute_True() {
        Grid grid = new Grid(new TestGridStrategy());
        Bishop bishop = new Bishop(Color.WHITE, 'b', '2');
        Bishop opponent = new Bishop(Color.BLACK, 'c', '3');
        grid.lines().assign(new Position("b2"), bishop);
        grid.lines().assign(new Position("c3"), opponent);
        assertThatCode(() -> {
            bishop.validateRoute(opponent, grid.lines());
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Bishop이 갈 수 없는 위치.")
    public void validateMove_False() {
        Grid grid = new Grid(new TestGridStrategy());
        Bishop bishop = new Bishop(Color.WHITE, 'b', '2');
        grid.lines().assign(new Position("b2"), bishop);
        assertThatThrownBy(() -> {
            bishop.validateSteps(new Empty('c', '5'), grid.lines());
        }).isInstanceOf(ChessException.class).hasMessage("이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("Bishop이 방해물 때문에 갈 수 없는 위치.")
    public void validateMove_FalseWhenObstaclesAhead() {
        Grid grid = new Grid(new TestGridStrategy());
        Bishop bishop = new Bishop(Color.WHITE, 'b', '2');
        Bishop obstacle = new Bishop(Color.WHITE, 'c', '3');
        grid.lines().assign(new Position("b2"), bishop);
        grid.lines().assign(new Position("c3"), obstacle);
        assertThatThrownBy(() -> {
            bishop.validateRoute(new Empty('d', '4'), grid.lines());
        }).isInstanceOf(ChessException.class).hasMessage("이동할 수 없는 위치입니다.");
    }
}
