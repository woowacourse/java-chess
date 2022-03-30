package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Point;
import chess.domain.board.Route;

class RookTest {

    @Test
    @DisplayName("룩을 만든다.")
    void createPiece() {
        Piece piece = new Rook(Color.WHITE);

        assertThat(piece).isNotNull();
    }

    @Test
    @DisplayName("상하좌우의 직선으로 이동할 수 있다.")
    void movableTest() {
        Route route = Route.of(List.of("a1", "a7"));
        Piece piece = new Rook(Color.WHITE);
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("장애물이 있을 경우 이동할 수 없다.")
    void notMovableWithObstacle() {
        Route route = Route.of(List.of("a1", "a7"));
        Piece piece = new Rook(Color.WHITE);
        EmptyPoints emptyPoints = EmptyPointsFixtures.except(Point.of("a5"));

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isFalse();
    }

    @Test
    @DisplayName("대각선으로 이동할 수 없다.")
    void notMovableWithDiagonal() {
        Route route = Route.of(List.of("a1", "c3"));
        Piece piece = new Rook(Color.WHITE);
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isFalse();
    }
}
