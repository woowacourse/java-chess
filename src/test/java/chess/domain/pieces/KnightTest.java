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

@DisplayName("나이트")
class KnightTest {

    @Nested
    @DisplayName("나이트가 움직일 수 있는 경우")
    class CanMove {

        Piece knight;

        @BeforeEach
        void setUp() {
            knight = new Knight(Color.WHITE);
        }

        @DisplayName("오른쪽 위 대각선으로 두 칸, 한 칸 이동할 수 있다")
        @Test
        void canMoveUpRight() {
            //given
            Square source = Square.from("e4");
            Square target = Square.from("f6");

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(knight.canMove(movement, null)).isTrue();
        }

        @DisplayName("오른쪽 아래 대각선으로 두 칸, 한 칸 이동할 수 있다")
        @Test
        void canMoveDownRight() {
            //given
            Square source = Square.from("e4");
            Square target = Square.from("f2");

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(knight.canMove(movement, null)).isTrue();
        }

        @DisplayName("왼쪽 위 대각선으로 두 칸, 한 칸 이동할 수 있다")
        @Test
        void canMoveUpLeft() {
            //given
            Square source = Square.from("e4");
            Square target = Square.from("d6");

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(knight.canMove(movement, null)).isTrue();
        }

        @DisplayName("왼쪽 아래 대각선으로 두 칸, 한 칸 이동할 수 있다")
        @Test
        void canMoveDownLeft() {
            //given
            Square source = Square.from("e4");
            Square target = Square.from("d2");

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(knight.canMove(movement, null)).isTrue();
        }

        @DisplayName("오른쪽으로 두 칸, 한 칸 위아래로 이동할 수 있다")
        @Test
        void canMoveRightUpAndDown() {
            //given
            Square source = Square.from("e4");
            Square target1 = Square.from("g5");
            Square target2 = Square.from("g3");

            //when
            Movement movement1 = new Movement(source, target1);
            Movement movement2 = new Movement(source, target2);

            //then
            assertThat(knight.canMove(movement1, null)).isTrue();
            assertThat(knight.canMove(movement2, null)).isTrue();
        }

        @DisplayName("왼쪽으로 두 칸, 한 칸 위아래로 이동할 수 있다")
        @Test
        void canMoveLeftUpAndDown() {
            //given
            Square source = Square.from("e4");
            Square target1 = Square.from("c5");
            Square target2 = Square.from("c3");

            //when
            Movement movement1 = new Movement(source, target1);
            Movement movement2 = new Movement(source, target2);

            //then
            assertThat(knight.canMove(movement1, null)).isTrue();
            assertThat(knight.canMove(movement2, null)).isTrue();
        }
    }

    @Nested
    @DisplayName("나이트가 움직일 수 없는 경우")
    class CanNotMove {

        Piece knight;

        @BeforeEach
        void setUp() {
            knight = new Knight(Color.WHITE);
        }

        @DisplayName("대각선 방향으로 이동할 수 없다")
        @Test
        void canNotMoveDiagonally() {
            //given
            Square source = Square.from("e4");
            Square target = Square.from("d5");

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(knight.canMove(movement, null)).isFalse();
        }

        @DisplayName("수평 또는 수직 방향으로 이동할 수 없다")
        @Test
        void canNotMoveHorizontallyOrVertically() {
            //given
            Square source = Square.from("e4");
            Square target1 = Square.from("e6");
            Square target2 = Square.from("g4");

            //when
            Movement movement1 = new Movement(source, target1);
            Movement movement2 = new Movement(source, target2);

            //then
            assertThat(knight.canMove(movement1, null)).isFalse();
            assertThat(knight.canMove(movement2, null)).isFalse();
        }
    }
}
