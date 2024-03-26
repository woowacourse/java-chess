package chess.domain.pieces.pawn;

import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.square.Movement;
import chess.domain.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("검은 폰")
class BlackPawnTest {

    @DisplayName("움직일 수 있는 경우")
    @Nested
    class CanMove {

        Piece blackPawn;

        @BeforeEach
        void setUp() {
            blackPawn = Pawn.of(Color.BLACK);
        }

        @DisplayName("시작위치에서는 2칸 전진할 수 있다")
        @Test
        void canMoveTwoStep() {
            //given
            Square blackSource = Square.from("a7");
            Square blackTarget = Square.from("a5");
            Movement blackMovement = new Movement(blackSource, blackTarget);

            //when
            boolean blackCanMove = blackPawn.canMove(blackMovement, null);

            //then
            assertThat(blackCanMove).isTrue();
        }

        @DisplayName("1칸 전진할 수 있다")
        @Test
        void canMoveOneStep() {
            //given
            Square blackSource = Square.from("a6");
            Square blackTarget = Square.from("a5");
            Movement blackMovement = new Movement(blackSource, blackTarget);

            //when
            boolean blackCanMove = blackPawn.canMove(blackMovement, null);

            //then
            assertThat(blackCanMove).isTrue();
        }

        @DisplayName("상대 기물이 있을 경우 대각선으로 공격할 수 있다")
        @Test
        void canAttack() {
            //given
            Square blackSource = Square.from("d7");
            Square blackTarget = Square.from("c6");
            Movement blackMovement = new Movement(blackSource, blackTarget);

            //when
            boolean blackCanMove = blackPawn.canMove(blackMovement, Pawn.of(Color.WHITE));

            //then
            assertThat(blackCanMove).isTrue();
        }
    }

    @DisplayName("움직일 수 없는 경우")
    @Nested
    class CanNotMove {

        Piece blackPawn;

        @BeforeEach
        void setUp() {
            blackPawn = Pawn.of(Color.BLACK);
        }

        @DisplayName("시작위치가 아닌 경우 2칸 이동할 수 없다")
        @Test
        void canNotMoveTwoStep() {
            //given
            Square blackSource = Square.from("a5");
            Square blackTarget = Square.from("a3");
            Movement blackMovement = new Movement(blackSource, blackTarget);

            //when
            boolean blackCanMove = blackPawn.canMove(blackMovement, null);

            //then
            assertThat(blackCanMove).isFalse();
        }

        @DisplayName("상대 기물이 없을 경우 공격할 수 없다")
        @Test
        void canNotAttack() {
            //given
            Square blackSource = Square.from("d7");
            Square blackTarget = Square.from("c6");
            Movement blackMovement = new Movement(blackSource, blackTarget);

            //when
            boolean blackCanMove = blackPawn.canMove(blackMovement, null);

            //then
            assertThat(blackCanMove).isFalse();
        }

        @DisplayName("상대 기물이 바로 앞에 있을 경우 공격할 수 없다")
        @Test
        void canNotAttackOneStep() {
            //given
            Square blackSource = Square.from("d7");
            Square blackTarget = Square.from("d6");
            Movement blackMovement = new Movement(blackSource, blackTarget);

            //when
            boolean blackCanMove = blackPawn.canMove(blackMovement, Pawn.of(Color.WHITE));

            //then
            assertThat(blackCanMove).isFalse();
        }
    }
}
