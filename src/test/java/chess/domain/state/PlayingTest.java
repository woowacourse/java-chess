package chess.domain.state;

import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.TestGridStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import chess.exception.ChessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayingTest {
    @Test
    @DisplayName("start를 실행했을 때 예외 발생")
    public void start() {
        assertThatThrownBy(() -> {
            new Playing().start();
        }).isInstanceOf(ChessException.class).hasMessage("이미 게임이 시작했습니다.");
    }

    @Test
    @DisplayName("end를 실행했을 때, Finished 상태를 반환")
    public void end() {
        assertThat(new Playing().end().getClass()).isEqualTo(Finished.class);
    }

    @Test
    @DisplayName("move를 실행했을 때 King을 잡을 경우, Finished 상태를 반환")
    public void move_ReturnFinished() {
        Grid grid = new Grid(new TestGridStrategy());
        Pawn pawn = new Pawn(Color.WHITE, 'b', '2');
        King king = new King(Color.BLACK, 'c', '3');
        grid.lines().assign(new Position("b2"), pawn);
        grid.lines().assign(new Position("c3"), king);
        GameState gameState = new Playing().move(grid, pawn.position(), king.position());
        assertThat(gameState.getClass()).isEqualTo(Finished.class);
    }

    @Test
    @DisplayName("move를 실행했을 때 King을 잡지 않은 경우, Playing 상태를 반환")
    public void move_ReturnPlaying() {
        Grid grid = new Grid(new TestGridStrategy());
        Pawn pawn = new Pawn(Color.WHITE, 'b', '2');
        Rook rook = new Rook(Color.BLACK, 'c', '3');
        grid.lines().assign(new Position("b2"), pawn);
        grid.lines().assign(new Position("c3"), rook);
        GameState gameState = new Playing().move(grid, pawn.position(), rook.position());
        assertThat(gameState.getClass()).isEqualTo(Playing.class);
    }


    @Test
    @DisplayName("status를 실행했을 때 Finished 상태를 반환")
    public void status() {
        assertThat(new Playing().status().getClass()).isEqualTo(Finished.class);
    }
}
