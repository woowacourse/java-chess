package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Point;
import chess.domain.board.Route;

public class PawnTest {

    @Test
    @DisplayName("폰을 만든다.")
    void createPiece() {
        Piece piece = new Pawn(Color.WHITE);

        assertThat(piece).isNotNull();
    }

    @Test
    @DisplayName("(흰색 폰) 한 칸 움직인다.")
    void moveForwardOneStepWhite() {
        Piece piece = new Pawn(Color.WHITE);

        Route route = Route.of(List.of("a2", "a3"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("(검정 폰) 한 칸 움직인다.")
    void moveForwardOneStepBlack() {
        Piece piece = new Pawn(Color.BLACK);
        Route route = Route.of(List.of("b7", "b6"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("(흰색 폰) 초기 위치에서 두 칸 움직인다.")
    void moveForwardTwoStepWhite() {
        Piece piece = new Pawn(Color.WHITE);
        Route route = Route.of(List.of("a2", "a4"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("(검정 폰) 초기 위치에서 두 칸 움직인다.")
    void moveForwardTwoStepBlack() {
        Piece piece = new Pawn(Color.BLACK);
        Route route = Route.of(List.of("b7", "b5"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.ALL;

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("폰이 초기 위치에서 두 칸을 움직일 떄, 도착 위치에 말이 있으면 예외가 발생한다.")
    void moveForwardTwoStepExceptionTest() {
        Piece piece = new Pawn(Color.BLACK);
        Route route = Route.of(List.of("b7", "b5"));

        EmptyPoints emptyPoints = EmptyPointsFixtures.except(Point.of("b5"));

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isFalse();
    }

    @Test
    @DisplayName("폰이 초기 위치에서 두 칸을 움직일 떄, 중간 위치에 말이 있으면 이동할 수 없다.")
    void moveForwardTwoStepExceptionTest2() {
        Piece piece = new Pawn(Color.BLACK);
        Route route = Route.of(List.of("b7", "b5"));

        EmptyPoints emptyPoints = EmptyPointsFixtures.except(Point.of(2, 5));

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isFalse();
    }

    @Test
    @DisplayName("(흰색 폰)적이 있다면 대각선으로 한 칸 이동 가능하다.")
    void moveDiagonalWhite() {
        Piece piece = new Pawn(Color.WHITE);
        Route route = Route.of(List.of("a4", "b5"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.except(Point.of(2, 5));

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("(검정 폰)적이 있다면 대각선으로 한 칸 이동 가능하다.")
    void moveDiagonalBlack() {
        Piece piece = new Pawn(Color.BLACK);
        Route route = Route.of(List.of("a5", "b4"));

        EmptyPoints emptyPoints = EmptyPointsFixtures.except(Point.of(2, 4));

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("(흰색 폰)시작위치에서는 적이 있어도 대각선으로 이동할 수 있다.")
    void moveDiagonalFromStartLineWhite() {
        Piece piece = new Pawn(Color.WHITE);
        Route route = Route.of(List.of("a2", "b3"));
        EmptyPoints emptyPoints = EmptyPointsFixtures.except(Point.of("b3"));

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("(검정 폰)시작위치에서는 적이 있어도 대각선으₩₩로 이동할 수 있다.")
    void moveDiagonalFromStartLineBlack() {
        Piece piece = new Pawn(Color.BLACK);
        Route route = Route.of(List.of("a7", "b6"));

        EmptyPoints emptyPoints = EmptyPointsFixtures.except(Point.of("b6"));

        boolean isMovable = piece.move(route, emptyPoints);

        assertThat(isMovable).isTrue();
    }
}
