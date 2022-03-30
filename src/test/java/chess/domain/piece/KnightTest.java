package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Point;
import chess.domain.board.Route;

public class KnightTest {

    @Test
    @DisplayName("나이트를 만든다.")
    void createPiece() {
        Piece piece = new Knight(Color.WHITE);

        assertThat(piece).isNotNull();
    }

    @Test
    @DisplayName("나이트를 움직인다.")
    void move() {
        Piece piece = new Knight(Color.WHITE);
        Route route = Route.of(List.of("b1", "c3"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("나이트가 잘못된 위치로 이동할 수 없다.")
    void moveWrongPoint() {
        Piece piece = new Knight(Color.WHITE);
        Route route = Route.of(List.of("b1", "c4"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isFalse();
    }

    @Test
    @DisplayName("도착지에 적이 있어도 이동할 수 있다.")
    void moveWithEnemy() {
        Piece piece = new Knight(Color.WHITE);
        Route route = Route.of(List.of("b1", "c3"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.except(Point.of("b3"));

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("나이트는 장애물을 뛰어넘을 수 있다.")
    void moveWithObstacle() {
        Piece piece = new Knight(Color.WHITE);
        Route route = Route.of(List.of("b1", "c3"));

        EmptyPoints emptyPoints = EmptyPointsFixtures.except(Point.of("b2"));

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

}
