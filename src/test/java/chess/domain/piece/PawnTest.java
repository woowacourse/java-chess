package chess.domain.piece;

import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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
    @DisplayName("폰이 움직였는 지 여부를 테스트")
    public void hasMoved_True() {
        Grid grid = new Grid(new NormalGridStrategy());
        Pawn pawn = new Pawn(Color.WHITE, 'b', '2');
        pawn.validateSteps(new Empty('b', '3'), grid.lines());
        assertThat(pawn.hasMoved()).isTrue();
    }

    @Test
    @DisplayName("폰이 움직였는 지 여부를 테스트")
    public void hasMoved_False() {
        Grid grid = new Grid(new NormalGridStrategy());
        Pawn pawn = new Pawn(Color.WHITE, 'b', '2');
        assertThat(pawn.hasMoved()).isFalse();
    }
}