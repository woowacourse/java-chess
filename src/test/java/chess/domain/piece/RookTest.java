package chess.domain.piece;

import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    @Test
    @DisplayName("룩 움직일 수 있는 좌표 확인")
    void movablePositionTest() {
        Rook rook = new Rook(Team.BLACK);
        List<Position> positions = rook.searchMovablePositions(Position.of(Horizontal.B, Vertical.ONE));

        assertThat(positions).containsExactly(
                Position.of(Horizontal.A, Vertical.ONE),
                Position.of(Horizontal.C, Vertical.ONE),
                Position.of(Horizontal.D, Vertical.ONE),
                Position.of(Horizontal.E, Vertical.ONE),
                Position.of(Horizontal.F, Vertical.ONE),
                Position.of(Horizontal.G, Vertical.ONE),
                Position.of(Horizontal.H, Vertical.ONE),
                Position.of(Horizontal.B, Vertical.EIGHT),
                Position.of(Horizontal.B, Vertical.SEVEN),
                Position.of(Horizontal.B, Vertical.SIX),
                Position.of(Horizontal.B, Vertical.FIVE),
                Position.of(Horizontal.B, Vertical.FOUR),
                Position.of(Horizontal.B, Vertical.THREE),
                Position.of(Horizontal.B, Vertical.TWO)
        );
    }
}