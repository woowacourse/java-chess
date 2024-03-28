package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Movement;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("킹")
class KingTest {

    @Nested
    @DisplayName("킹이 움직일 수 있는 경우")
    class CanMove {

        Piece king;

        @BeforeEach
        void setUp() {
            king = new King(Color.WHITE);
        }

        @DisplayName("한 칸 위로 움직일 수 있다")
        @Test
        void canMoveOneSquareUp() {
            //given
            Square source = Square.of(File.E, Rank.ONE);
            Square target = Square.of(File.E, Rank.TWO);

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(king.canMove(movement, null)).isTrue();
        }

        @DisplayName("한 칸 아래로 움직일 수 있다")
        @Test
        void canMoveOneSquareDown() {
            //given
            Square source = Square.of(File.E, Rank.TWO);
            Square target = Square.of(File.E, Rank.ONE);

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(king.canMove(movement, null)).isTrue();
        }

        @DisplayName("한 칸 오른쪽으로 움직일 수 있다")
        @Test
        void canMoveOneSquareRight() {
            //given
            Square source = Square.of(File.E, Rank.ONE);
            Square target = Square.of(File.F, Rank.ONE);

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(king.canMove(movement, null)).isTrue();
        }

        @DisplayName("한 칸 왼쪽으로 움직일 수 있다")
        @Test
        void canMoveOneSquareLeft() {
            //given
            Square source = Square.of(File.F, Rank.ONE);
            Square target = Square.of(File.E, Rank.ONE);

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(king.canMove(movement, null)).isTrue();
        }

        @DisplayName("대각선으로 왼쪽 위로 움직일 수 있다")
        @Test
        void canMoveDiagonallyUpLeft() {
            //given
            Square source = Square.of(File.E, Rank.ONE);
            Square target = Square.of(File.D, Rank.TWO);

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(king.canMove(movement, null)).isTrue();
        }

        @DisplayName("대각선으로 오른쪽 위로 움직일 수 있다")
        @Test
        void canMoveDiagonallyUpRight() {
            //given
            Square source = Square.of(File.E, Rank.ONE);
            Square target = Square.of(File.F, Rank.TWO);

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(king.canMove(movement, null)).isTrue();
        }

        @DisplayName("대각선으로 왼쪽 아래로 움직일 수 있다")
        @Test
        void canMoveDiagonallyDownLeft() {
            //given
            Square source = Square.of(File.E, Rank.TWO);
            Square target = Square.of(File.D, Rank.ONE);

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(king.canMove(movement, null)).isTrue();
        }

        @DisplayName("대각선으로 오른쪽 아래로 움직일 수 있다")
        @Test
        void canMoveDiagonallyDownRight() {
            //given
            Square source = Square.of(File.E, Rank.ONE);
            Square target = Square.of(File.F, Rank.TWO);

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(king.canMove(movement, null)).isTrue();
        }
    }

    @Nested
    @DisplayName("킹이 움직일 수 없는 경우")
    class CanNotMove {

        Piece king;

        @BeforeEach
        void setUp() {
            king = new King(Color.WHITE);
        }

        @DisplayName("대각선으로 두 칸 이동할 수 없다")
        @Test
        void canNotMoveDiagonally() {
            //given
            Square source = Square.of(File.E, Rank.ONE);
            Square target = Square.of(File.C, Rank.THREE);

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(king.canMove(movement, null)).isFalse();
        }

        @DisplayName("두 칸 위로 움직일 수 없다")
        @Test
        void canNotMoveTwoSquaresUp() {
            //given
            Square source = Square.of(File.E, Rank.ONE);
            Square target = Square.of(File.E, Rank.THREE);

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(king.canMove(movement, null)).isFalse();
        }
    }
}
