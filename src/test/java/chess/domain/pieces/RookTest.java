package chess.domain.pieces;

import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.square.Movement;
import chess.domain.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("룩")
class RookTest {

    @Nested
    @DisplayName("룩이 움직일 수 있는 경우")
    class CanMove {

        Piece rook;

        @BeforeEach
        void setUp() {
            rook = new Rook(Color.WHITE);
        }

        @DisplayName("수평으로 두 칸 움직일 수 있다")
        @Test
        void canMoveHorizontally() {
            //given
            Square source = Square.from("a1");
            Square target = Square.from("c1");

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(rook.canMove(movement, null)).isTrue();
        }

        @DisplayName("수직으로 세 칸 움직일 수 있다")
        @Test
        void canMoveVertically() {
            //given
            Square source = Square.from("a1");
            Square target = Square.from("a4");

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(rook.canMove(movement, null)).isTrue();
        }

        @DisplayName("수평으로 한 칸 이동 후 세로로 다섯 칸 움직일 수 있다")
        @Test
        void canMoveHorizontallyThenVertically() {
            //given
            Square source = Square.from("a1");
            Square intermediate = Square.from("b1");
            Square target = Square.from("b5");

            //when
            Movement movement1 = new Movement(source, intermediate);
            Movement movement2 = new Movement(intermediate, target);

            //then
            assertThat(rook.canMove(movement1, null)).isTrue();
            assertThat(rook.canMove(movement2, null)).isTrue();
        }
    }

    @Nested
    @DisplayName("룩이 움직일 수 없는 경우")
    class CanNotMove {

        Piece rook;

        @BeforeEach
        void setUp() {
            rook = new Rook(Color.WHITE);
        }

        @DisplayName("대각선으로 이동할 수 없다")
        @Test
        void canNotMoveDiagonally() {
            //given
            Square source = Square.from("a1");
            Square target = Square.from("b2");

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(rook.canMove(movement, null)).isFalse();
        }

        @DisplayName("나이트처럼 L자 모양으로 이동할 수 없다")
        @Test
        void canNotMoveLikeKnight() {
            //given
            Square source = Square.from("a1");
            Square target = Square.from("c2");

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(rook.canMove(movement, null)).isFalse();
        }
    }
}
