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

public class KingTest {
    @Test
    @DisplayName("King 정상적으로 생성하는 지 테스트")
    public void init() {
        assertThatCode(() -> {
            new King(Color.BLACK, 'a', '2');
            new King(Color.BLACK, 'a', 2);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("King의 서로 다른 생성자에 따라서, 올바르게 포지션이 들어가는 지 테스트")
    public void init_isSamePosition() {
        Piece pawn = new King(Color.BLACK, 'a', '2');
        Piece pawn2 = new King(Color.BLACK, 'a', 2);
        Position expectedPosition = new Position("a2");
        assertThat(pawn.position()).isEqualTo(expectedPosition);
        assertThat(pawn2.position()).isEqualTo(expectedPosition);
    }

    @Test
    @DisplayName("King이 갈 수 있는 위치 검증, true")
    public void validateStep_True() {
        Grid grid = new Grid(new TestGridStrategy());
        King king = new King(Color.WHITE, 'b', '2');
        grid.lines().assign(new Position("b2"), king);
        assertThatCode(() -> {
            king.validateSteps(new Empty('c', '3'), grid.lines());
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("King이 갈 수 있는 위치: 상대말 먹기")
    public void validateRoute_True() {
        Grid grid = new Grid(new TestGridStrategy());
        King king = new King(Color.WHITE, 'b', '2');
        King opponent = new King(Color.BLACK, 'c', '3');
        grid.lines().assign(new Position("b2"), king);
        grid.lines().assign(new Position("c3"), opponent);
        assertThatCode(() -> {
            king.validateRoute(opponent, grid.lines());
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("King이 갈 수 없는 위치.")
    public void validateMove_False() {
        Grid grid = new Grid(new TestGridStrategy());
        King king = new King(Color.WHITE, 'b', '2');
        grid.lines().assign(new Position("b2"), king);
        assertThatThrownBy(() -> {
            king.validateSteps(new Empty('c', '4'), grid.lines());
        }).isInstanceOf(ChessException.class).hasMessage("이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("King이 방해물 때문에 갈 수 없는 위치.")
    public void validateMove_FalseWhenObstaclesAhead() {
        Grid grid = new Grid(new TestGridStrategy());
        King king = new King(Color.WHITE, 'b', '2');
        King obstacle = new King(Color.WHITE, 'c', '3');
        grid.lines().assign(new Position("b2"), king);
        grid.lines().assign(new Position("c3"), obstacle);
        assertThatThrownBy(() -> {
            king.validateRoute(obstacle, grid.lines());
        }).isInstanceOf(ChessException.class).hasMessage("이동할 수 없는 위치입니다.");
    }
}
