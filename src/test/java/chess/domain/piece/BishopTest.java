package chess.domain.piece;

import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @Test
    @DisplayName("비숍 움직일 수 있는 좌표 확인")
    void movablePositionsTest() {
        Bishop bishop = new Bishop(Team.BLACK);
        List<Position> positions = bishop.searchMovablePositions(Position.of(Horizontal.D, Vertical.FOUR));

        assertThat(positions).containsExactly(
                Position.of(Horizontal.E, Vertical.FIVE),
                Position.of(Horizontal.F, Vertical.SIX),
                Position.of(Horizontal.G, Vertical.SEVEN),
                Position.of(Horizontal.H, Vertical.EIGHT),
                Position.of(Horizontal.C, Vertical.FIVE),
                Position.of(Horizontal.B, Vertical.SIX),
                Position.of(Horizontal.A, Vertical.SEVEN),
                Position.of(Horizontal.C, Vertical.THREE),
                Position.of(Horizontal.B, Vertical.TWO),
                Position.of(Horizontal.A, Vertical.ONE),
                Position.of(Horizontal.E, Vertical.THREE),
                Position.of(Horizontal.F, Vertical.TWO),
                Position.of(Horizontal.G, Vertical.ONE)
        );
    }

    @Test
    @DisplayName("비숍 모서리에서 움직일 수 있는 좌표 확인")
    void movablePositionsWhenBorderTest() {
        Bishop bishop = new Bishop(Team.BLACK);
        List<Position> positions = bishop.searchMovablePositions(Position.of(Horizontal.A, Vertical.ONE));

        assertThat(positions).containsExactly(
                Position.of(Horizontal.B, Vertical.TWO),
                Position.of(Horizontal.C, Vertical.THREE),
                Position.of(Horizontal.D, Vertical.FOUR),
                Position.of(Horizontal.E, Vertical.FIVE),
                Position.of(Horizontal.F, Vertical.SIX),
                Position.of(Horizontal.G, Vertical.SEVEN),
                Position.of(Horizontal.H, Vertical.EIGHT)
        );
    }
}