package chess.domain.chesspiece;

import chess.domain.Position;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RookTest {
    @Test
    void 이동가능한_경로_룩() {
        Position position = Position.of(0, 0);
        ChessPiece rook = new Rook(Team.BLACK);
        List<Position> route = rook.getRouteOfPiece(position, Position.of(0, 7));
        List<Position> testRoute = IntStream.rangeClosed(1, 7).mapToObj(i -> Position.of(0, i)).collect(Collectors.toList());

        for (int i = 0; i < 7; i++) {
            assertThat(route.contains(testRoute.get(i))).isTrue();
        }
    }

    @Test
    void 이동불가능한_경로_예외발생_룩() {
        Position position = Position.of(0, 0);
        ChessPiece rook = new Rook(Team.BLACK);

        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                int iFinal = i;
                int jFinal = j;
                assertThrows(IllegalArgumentException.class, () -> rook.getRouteOfPiece(position, Position.of(iFinal, jFinal)));
            }
        }
    }
}