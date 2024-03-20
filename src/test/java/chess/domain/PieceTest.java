package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Nested
    class PawnMove {

        @Test
        @DisplayName("폰이 직선으로 이동할 경우 경로에 어떠한 말도 없다면 이동 가능하다.")
        void canMoveStraight() {
            Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE);
            assertAll(() -> assertThat(
                            whitePawn.canMove(new Position(1, 1), new Position(2, 1), true, Map.of())).isTrue(),
                    () -> assertThat(
                            whitePawn.canMove(new Position(1, 1), new Position(3, 1), true, Map.of())).isTrue(),
                    () -> assertThat(
                            whitePawn.canMove(new Position(1, 1), new Position(2, 1), false, Map.of())).isTrue());
        }

        @Test
        @DisplayName("폰이 대각선 이동할 경우 도착 위치에 상대방의 말이 있다면 이동 가능하다.")
        void canMoveDiagonal() {
            Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE);
            assertAll(() -> assertThat(whitePawn.canMove(new Position(1, 3), new Position(2, 2), false,
                    Map.of(new Position(2, 2), new Piece(PieceType.QUEEN, Color.BLACK)))).isTrue(), () -> assertThat(
                    whitePawn.canMove(new Position(1, 3), new Position(2, 4), false,
                            Map.of(new Position(2, 4), new Piece(PieceType.QUEEN, Color.BLACK)))).isTrue());
        }

        @Test
        @DisplayName("폰이 직선으로 이동할 경우 도착 위치에 말이 있다면 이동이 불가능하다.")
        void canNotMoveStraightWhenPieceExistInToPosition() {
            Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE);
            assertAll(
                    () -> assertThat(
                            whitePawn.canMove(new Position(1, 1), new Position(2, 1), true,
                                    Map.of(new Position(2, 1), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse(),
                    () -> assertThat(
                            whitePawn.canMove(new Position(1, 1), new Position(2, 1), true,
                                    Map.of(new Position(2, 1), new Piece(PieceType.QUEEN, Color.BLACK)))).isFalse());
        }


        @Test
        @DisplayName("폰이 직선으로 이동할 경우 도착 위치 전의 경로에 말이 있다면 이동이 불가능하다.")
        void canNotMoveStraightWhenPieceExistInPath() {
            Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE);
            assertAll(
                    () -> assertThat(
                            whitePawn.canMove(new Position(1, 1), new Position(3, 1), true,
                                    Map.of(new Position(2, 1), new Piece(PieceType.QUEEN, Color.BLACK)))).isFalse(),
                    () -> assertThat(
                            whitePawn.canMove(new Position(1, 1), new Position(3, 1), true,
                                    Map.of(new Position(2, 1), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse());
        }

        @Test
        @DisplayName("폰이 대각선으로 이동할 경우 도착 위치가 같은 색의 말인 경우 이동이 불가능하다.")
        void canNotMoveDiagonalWhenToPositionIsSameColor() {
            Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE);
            assertAll(
                    () -> assertThat(
                            whitePawn.canMove(new Position(1, 3), new Position(2, 2), true,
                                    Map.of(new Position(2, 2), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse(),
                    () -> assertThat(
                            whitePawn.canMove(new Position(1, 3), new Position(2, 4), true,
                                    Map.of(new Position(2, 4), new Piece(PieceType.QUEEN, Color.WHITE)))).isFalse());
        }

        @Test
        @DisplayName("폰이 대각선으로 이동할 경우 도착 위치가 비어있을 경우 이동이 불가능하다.")
        void canNotMoveDiagonalWhenToPositionIsEmpty() {
            Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE);
            assertAll(
                    () -> assertThat(
                            whitePawn.canMove(new Position(1, 3), new Position(2, 2), true,
                                    Map.of())).isFalse(),
                    () -> assertThat(
                            whitePawn.canMove(new Position(1, 3), new Position(2, 4), true,
                                    Map.of())).isFalse());
        }
    }
    
}

