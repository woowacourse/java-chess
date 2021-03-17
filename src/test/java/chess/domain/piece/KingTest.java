package chess.domain.piece;

import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class KingTest {
    @Test
    @DisplayName("킹 움직일 수 있는 좌표 확인")
    void movablePositionTest() {
        King king = new King(Team.BLACK);
        List<Position> positions = king.movablePositions(Position.of(Horizontal.B, Vertical.ONE));

        assertThat(positions).containsExactly(
                Position.of(Horizontal.B, Vertical.TWO),
                Position.of(Horizontal.C, Vertical.TWO),
                Position.of(Horizontal.C, Vertical.ONE),
                Position.of(Horizontal.A, Vertical.ONE),
                Position.of(Horizontal.A, Vertical.TWO)
        );
    }
}