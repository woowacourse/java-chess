package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;

public class KingTest {

    @Test
    @DisplayName("킹을 만든다.")
    void createPiece() {
        Piece piece = new King(Color.WHITE);

        assertThat(piece).isNotNull();
    }

    @Test
    @DisplayName("앞으로 한 칸 움직인다.")
    void moveForwardOneStep() {
        Piece piece = new King(Color.BLACK);
        Route route = Route.of(List.of("e1", "e2"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("대각선으로 한 칸 움직인다.")
    void moveDiagonalOneStep() {
        Piece piece = new King(Color.BLACK);
        Route route = Route.of(List.of("e1", "d2"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("옆으로 한 칸 움직인다.")
    void moveSideOneStep() {
        Piece piece = new King(Color.BLACK);
        Route route = Route.of(List.of("e1", "d1"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("킹은 두 칸 이상 움직이지 못한다.")
    void notMovable() {
        Piece piece = new King(Color.BLACK);
        Route route = Route.of(List.of("e1", "d4"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isFalse();
    }
}
