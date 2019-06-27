package chess.domain.chesspiece;

import chess.domain.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    @Test
    void 이동가능한_경로_나이트() {
        Position position = Position.of(0, 1);
        ChessPiece knight = new Knight(Team.BLACK);
        List<Position> route = knight.getRouteOfPiece(position, Position.of(2, 2));
        List<Position> testRoute = new ArrayList<>();

        testRoute.add(Position.of(2, 2));

        assertThat(route.contains(testRoute.get(0))).isTrue();
    }

    @Test
    void 이동불가능한_경로_예외발생_나이트() {
        Position position = Position.of(0, 1);
        ChessPiece knight = new Knight(Team.BLACK);

        assertThrows(IllegalArgumentException.class, () -> knight.getRouteOfPiece(position, Position.of(1, 1)));
    }
}