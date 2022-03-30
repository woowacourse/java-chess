package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Point;
import chess.domain.board.Route;

public class QueenTest {

    @Test
    @DisplayName("퀸을 만든다.")
    void createPiece() {
        Piece piece = new Queen(Color.WHITE);

        assertThat(piece).isNotNull();
    }

    @Test
    @DisplayName("상하좌우의 직선으로 이동할 수 있다.")
    void moveWithCross() {
        Route route = Route.of(List.of("a1", "a7"));
        Piece piece = new Queen(Color.WHITE);
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("대각선으로 이동할 수 있다.")
    void moveWithDiagonal() {
        Route route = Route.of(List.of("c1", "g5"));
        Piece piece = new Queen(Color.WHITE);
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("퀸은 직선으로만 이동할 수 있다.")
    void notMovableWithoutStraight() {
        Route route = Route.of(List.of("a1", "b7"));
        Piece piece = new Queen(Color.WHITE);
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isFalse();
    }

    @Test
    @DisplayName("장애물이 있을 경우 상하좌우로 이동할 수 없다.")
    void notMovableWithCrossObstacle() {
        Route route = Route.of(List.of("a1", "a7"));
        Piece piece = new Queen(Color.WHITE);
        EmptyPoints emptyPoints = EmptyPointsFixtures.except(Point.of("a5"));

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isFalse();
    }

    @Test
    @DisplayName("장애물이 있을 경우 대각선으로 이동할 수 없다.")
    void notMovableWithDiagonalObstacle() {
        Route route = Route.of(List.of("c1", "g5"));
        Piece piece = new Queen(Color.WHITE);

        EmptyPoints emptyPoints = EmptyPointsFixtures.except(Point.of("d2"));

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isFalse();
    }
}
