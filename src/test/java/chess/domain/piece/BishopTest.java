package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Point;
import chess.domain.board.Route;

public class BishopTest {

    @Test
    @DisplayName("비숍을 만든다.")
    void createPiece() {
        Piece piece = new Bishop(Color.WHITE);

        assertThat(piece).isNotNull();
    }

    @Test
    @DisplayName("대각선으로 이동할 수 있다.")
    void moveWithDiagonal() {
        Route route = Route.of(List.of("c1", "g5"));
        Piece piece = new Bishop(Color.WHITE);
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("대각선이 아닌곳은 이동이 불가능하다.")
    void throwsExceptionWithNotDiagonalDirection() {
        Route route = Route.of(List.of("c1", "a2"));
        Piece piece = new Bishop(Color.WHITE);
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isFalse();
    }

    @Test
    @DisplayName("장애물이 있을 경우 이동할 수 없다.")
    void notMovableWithObstacle() {
        Route route = Route.of(List.of("c1", "g5"));
        Piece piece = new Bishop(Color.WHITE);
        EmptyPoints emptyPoints = EmptyPointsFixtures.except(Point.of("d2"));

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isFalse();
    }

    @Test
    @DisplayName("상하 좌우로 이동할 수 없다.")
    void notMovableWithCross() {
        Route route = Route.of(List.of("c1", "c4"));
        Piece piece = new Bishop(Color.WHITE);
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isFalse();
    }
}
