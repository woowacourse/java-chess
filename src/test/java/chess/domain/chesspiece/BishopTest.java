package chess.domain.chesspiece;

import chess.domain.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BishopTest {
    @Test
    void 이동가능한_경로_비숍() {
        Position position = Position.of(3, 4);
        ChessPiece rook = new Bishop(Team.BLACK);
        List<Position> route = rook.getRouteOfPiece(position, Position.of(6, 1));
        List<Position> testRoute = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            testRoute.add(Position.of(position.getY() + i, position.getX() - i));
        }

        for (int i = 0; i < 3; i++) {
            assertThat(route.contains(testRoute.get(i))).isTrue();
        }
    }

    @Test
    void 이동불가능한_경로_예외발생_비숍() {
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