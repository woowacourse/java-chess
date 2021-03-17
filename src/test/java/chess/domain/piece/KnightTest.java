package chess.domain.piece;

import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @Test
    @DisplayName("나이트 움직일 수 있는 좌표 확인")
    void movablePositionTest() {
        Knight knight = new Knight(Team.BLACK);
        List<Position> positions = knight.movablePositions(Position.of(Horizontal.B, Vertical.ONE));

        assertThat(positions).containsExactly(
                Position.of(Horizontal.D, Vertical.TWO),
                Position.of(Horizontal.C, Vertical.THREE),
                Position.of(Horizontal.A, Vertical.THREE)
        );
    }
}