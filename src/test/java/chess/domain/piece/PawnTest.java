package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Movement;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("폰")
class PawnTest {

    @DisplayName("움직일 수 있는 경우")
    @Nested
    class Movable {

        @DisplayName("시작위치에서는 2칸 전진할 수 있다")
        @Test
        void canMoveTwoStep() {
            //given
            Square whiteSource = Square.from("a2");
            Square whiteDestination = Square.from("a4");
            Piece whitePawn = new Pawn(Color.WHITE);
            Movement whiteMovement = new Movement(whiteSource, whiteDestination);

            Square blackSource = Square.from("a7");
            Square blackDestination = Square.from("a5");
            Piece blackPawn = new Pawn(Color.BLACK);
            Movement blackMovement = new Movement(blackSource, blackDestination);

            //when
            boolean whiteCanMove = whitePawn.canMove(whiteMovement, null);
            boolean blackCanMove = blackPawn.canMove(blackMovement, null);

            //then
            assertThat(whiteCanMove).isTrue();
            assertThat(blackCanMove).isTrue();
        }

        @DisplayName("1칸 전진할 수 있다")
        @Test
        void canMoveOneStep() {
            //given
            Square whiteSource = Square.from("a3");
            Square whiteDestination = Square.from("a4");
            Piece whitePawn = new Pawn(Color.WHITE);
            Movement whiteMovement = new Movement(whiteSource, whiteDestination);

            Square blackSource = Square.from("a6");
            Square blackDestination = Square.from("a5");
            Piece blackPawn = new Pawn(Color.BLACK);
            Movement blackMovement = new Movement(blackSource, blackDestination);

            //when
            boolean whiteCanMove = whitePawn.canMove(whiteMovement, null);
            boolean blackCanMove = blackPawn.canMove(blackMovement, null);

            //then
            assertThat(whiteCanMove).isTrue();
            assertThat(blackCanMove).isTrue();
        }

        @DisplayName("상대 기물이 있을 경우 대각선으로 공격할 수 있다")
        @Test
        void canAttack() {
            //given
            Square whiteSource = Square.from("a2");
            Square whiteDestination = Square.from("b3");
            Piece whitePawn = new Pawn(Color.WHITE);
            Movement whiteMovement = new Movement(whiteSource, whiteDestination);

            Square blackSource = Square.from("d7");
            Square blackDestination = Square.from("c6");
            Piece blackPawn = new Pawn(Color.BLACK);
            Movement blackMovement = new Movement(blackSource, blackDestination);

            //when
            boolean whiteCanMove = whitePawn.canMove(whiteMovement, new Pawn(Color.BLACK));
            boolean blackCanMove = blackPawn.canMove(blackMovement, new Pawn(Color.WHITE));

            //then
            assertThat(whiteCanMove).isTrue();
            assertThat(blackCanMove).isTrue();
        }
    }

    @DisplayName("움직일 수 없는 경우")
    @Nested
    class Immovable {

        @DisplayName("시작위치가 아닌 경우 2칸 이동할 수 없다")
        @Test
        void canNotMoveTwoStep() {
            //given
            Square whiteSource = Square.from("a3");
            Square whiteDestination = Square.from("a5");
            Piece whitePawn = new Pawn(Color.WHITE);
            Movement whiteMovement = new Movement(whiteSource, whiteDestination);

            Square blackSource = Square.from("a5");
            Square blackDestination = Square.from("a3");
            Piece blackPawn = new Pawn(Color.BLACK);
            Movement blackMovement = new Movement(blackSource, blackDestination);

            //when
            boolean whiteCanMove = whitePawn.canMove(whiteMovement, null);
            boolean blackCanMove = blackPawn.canMove(blackMovement, null);

            //then
            assertThat(whiteCanMove).isFalse();
            assertThat(blackCanMove).isFalse();
        }

        @DisplayName("상대 기물이 없을 경우 공격할 수 없다")
        @Test
        void canNotAttack() {
            //given
            Square whiteSource = Square.from("a2");
            Square whiteDestination = Square.from("b3");
            Piece whitePawn = new Pawn(Color.WHITE);
            Movement whiteMovement = new Movement(whiteSource, whiteDestination);

            Square blackSource = Square.from("d7");
            Square blackDestination = Square.from("c6");
            Piece blackPawn = new Pawn(Color.BLACK);
            Movement blackMovement = new Movement(blackSource, blackDestination);

            //when
            boolean whiteCanMove = whitePawn.canMove(whiteMovement, null);
            boolean blackCanMove = blackPawn.canMove(blackMovement, null);

            //then
            assertThat(whiteCanMove).isFalse();
            assertThat(blackCanMove).isFalse();
        }
    }
}
