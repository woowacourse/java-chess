package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.square.Movement;
import chess.domain.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("비숍")
class BishopTest {

    @Nested
    @DisplayName("움직일 수 있는 경우")
    class CanMove {

        Piece bishop;

        @BeforeEach
        void setUp() {
            bishop = new Bishop(Color.WHITE);
        }

        @DisplayName("오른쪽 위 대각선으로 움직일 수 있다")
        @Test
        void canMoveDiagonallyUpRight() {
            //given
            Square source = Square.from("c3");
            Square target = Square.from("e5");

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(bishop.canMove(movement, null)).isTrue();
        }

        @DisplayName("왼쪽 위 대각선으로 움직일 수 있다")
        @Test
        void canMoveDiagonallyUpLeft() {
            //given
            Square source = Square.from("e3");
            Square target = Square.from("c5");

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(bishop.canMove(movement, null)).isTrue();
        }

        @DisplayName("오른쪽 아래 대각선으로 움직일 수 있다")
        @Test
        void canMoveDiagonallyDownRight() {
            //given
            Square source = Square.from("c5");
            Square target = Square.from("e3");

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(bishop.canMove(movement, null)).isTrue();
        }

        @DisplayName("왼쪽 아래 대각선으로 움직일 수 있다")
        @Test
        void canMoveDiagonallyDownLeft() {
            //given
            Square source = Square.from("e5");
            Square target = Square.from("c3");

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(bishop.canMove(movement, null)).isTrue();
        }
    }

    @Nested
    @DisplayName("움직일 수 없는 경우")
    class CanNotMove {

        Piece bishop;

        @BeforeEach
        void setUp() {
            bishop = new Bishop(Color.WHITE);
        }

        @DisplayName("수직으로 움직일 수 없다")
        @Test
        void canNotMoveVertically() {
            //given
            Square source = Square.from("c1");
            Square target = Square.from("c2");

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(bishop.canMove(movement, null)).isFalse();
        }

        @DisplayName("수평으로 움직일 수 없다")
        @Test
        void canNotMoveHorizontally() {
            //given
            Square source = Square.from("c1");
            Square target = Square.from("d1");

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(bishop.canMove(movement, null)).isFalse();
        }

        @DisplayName("대각선이 아닌 방향으로 움직일 수 없다")
        @Test
        void canNotMoveInNonDiagonalDirection() {
            //given
            Square source = Square.from("c1");
            Square target = Square.from("d3");

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(bishop.canMove(movement, null)).isFalse();
        }
    }
}
