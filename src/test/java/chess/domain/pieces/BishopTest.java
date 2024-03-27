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
            Square source = Square.of(File.C, Rank.THREE);
            Square target = Square.of(File.E, Rank.FIVE);

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(bishop.canMove(movement, null)).isTrue();
        }

        @DisplayName("왼쪽 위 대각선으로 움직일 수 있다")
        @Test
        void canMoveDiagonallyUpLeft() {
            //given
            Square source = Square.of(File.E, Rank.THREE);
            Square target = Square.of(File.C, Rank.FIVE);

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(bishop.canMove(movement, null)).isTrue();
        }

        @DisplayName("오른쪽 아래 대각선으로 움직일 수 있다")
        @Test
        void canMoveDiagonallyDownRight() {
            //given
            Square source = Square.of(File.C, Rank.FIVE);
            Square target = Square.of(File.E, Rank.THREE);

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(bishop.canMove(movement, null)).isTrue();
        }

        @DisplayName("왼쪽 아래 대각선으로 움직일 수 있다")
        @Test
        void canMoveDiagonallyDownLeft() {
            //given
            Square source = Square.of(File.E, Rank.FIVE);
            Square target = Square.of(File.C, Rank.THREE);

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
            Square source = Square.of(File.C, Rank.ONE);
            Square target = Square.of(File.C, Rank.TWO);

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(bishop.canMove(movement, null)).isFalse();
        }

        @DisplayName("수평으로 움직일 수 없다")
        @Test
        void canNotMoveHorizontally() {
            //given
            Square source = Square.of(File.C, Rank.ONE);
            Square target = Square.of(File.D, Rank.ONE);

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(bishop.canMove(movement, null)).isFalse();
        }

        @DisplayName("대각선이 아닌 방향으로 움직일 수 없다")
        @Test
        void canNotMoveInNonDiagonalDirection() {
            //given
            Square source = Square.of(File.C, Rank.ONE);
            Square target = Square.of(File.D, Rank.THREE);

            //when
            Movement movement = new Movement(source, target);

            //then
            assertThat(bishop.canMove(movement, null)).isFalse();
        }
    }
}
