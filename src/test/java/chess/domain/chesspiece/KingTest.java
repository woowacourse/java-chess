package chess.domain.chesspiece;

import chess.domain.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class KingTest {
    @Test
    void 이동가능한_경로_킹() {
        Position position = Position.of(0, 3);
        ChessPiece king = new King(Team.BLACK);
        List<Position> route = king.getRouteOfPiece(position, Position.of(1, 4));

        assertThat(route.contains(Position.of(1, 4))).isTrue();
    }

    @Test
    void 이동불가능한_경로_예외발생_킹() {
        Position position = Position.of(0, 3);
        ChessPiece king = new King(Team.BLACK);

        assertThrows(IllegalArgumentException.class, () -> king.getRouteOfPiece(position, Position.of(1, 5)));
    }
}