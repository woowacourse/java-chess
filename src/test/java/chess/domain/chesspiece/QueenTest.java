package chess.domain.chesspiece;

import chess.domain.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
    @Test
    void 이동가능한_경로_퀸() {
        Position position = Position.of(0, 4);
        ChessPiece queen = new Queen(Team.BLACK);
        List<Position> diagonalRoute = queen.getRouteOfPiece(position, Position.of(3, 1));
        List<Position> diagonalTestRoute = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            diagonalTestRoute.add(Position.of(position.getY() + i, position.getX() - i));
        }

        for (int i = 0; i < 3; i++) {
            assertThat(diagonalRoute.contains(diagonalTestRoute.get(i))).isTrue();
        }

        List<Position> route = queen.getRouteOfPiece(position, Position.of(5, 4));
        List<Position> testRoute = IntStream.rangeClosed(1, 5).mapToObj(i -> Position.of(i, 4)).collect(Collectors.toList());
        for (int i = 0; i < 5; i++) {
            assertThat(route.contains(testRoute.get(i))).isTrue();
        }
    }

    @Test
    void 이동불가능한_경로_예외발생_퀸() {
        Position position = Position.of(0, 0);
        ChessPiece queen = new Queen(Team.BLACK);

        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                if (i == j) {
                    continue;
                }
                int iFinal = i;
                int jFinal = j;
                assertThrows(IllegalArgumentException.class, () -> queen.getRouteOfPiece(position, Position.of(iFinal, jFinal)));
            }
        }
    }

}