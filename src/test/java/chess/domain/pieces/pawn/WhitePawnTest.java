package chess.domain.pieces.pawn;

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

@DisplayName("흰 폰")
class WhitePawnTest {

    @DisplayName("움직일 수 있는 경우")
    @Nested
    class CanMove {

        Piece whitePawn;

        @BeforeEach
        void setUp() {
            whitePawn = Pawn.of(Color.WHITE);
        }

        @DisplayName("시작위치에서는 2칸 전진할 수 있다")
        @Test
        void canMoveTwoStep() {
            //given
            Square whiteSource = Square.of(File.A, Rank.TWO);
            Square whiteTarget = Square.of(File.A, Rank.FOUR);
            Movement whiteMovement = new Movement(whiteSource, whiteTarget);

            //when
            boolean whiteCanMove = whitePawn.canMove(whiteMovement, null);

            //then
            assertThat(whiteCanMove).isTrue();
        }

        @DisplayName("1칸 전진할 수 있다")
        @Test
        void canMoveOneStep() {
            //given
            Square whiteSource = Square.of(File.A, Rank.THREE);
            Square whiteTarget = Square.of(File.A, Rank.FOUR);
            Movement whiteMovement = new Movement(whiteSource, whiteTarget);

            //when
            boolean whiteCanMove = whitePawn.canMove(whiteMovement, null);

            //then
            assertThat(whiteCanMove).isTrue();
        }

        @DisplayName("상대 기물이 있을 경우 대각선으로 공격할 수 있다")
        @Test
        void canAttack() {
            //given
            Square whiteSource = Square.of(File.A, Rank.TWO);
            Square whiteTarget = Square.of(File.B, Rank.THREE);
            Movement whiteMovement = new Movement(whiteSource, whiteTarget);

            //when
            boolean whiteCanMove = whitePawn.canMove(whiteMovement, Pawn.of(Color.BLACK));

            //then
            assertThat(whiteCanMove).isTrue();
        }
    }

    @DisplayName("움직일 수 없는 경우")
    @Nested
    class CanNotMove {

        Piece whitePawn;

        @BeforeEach
        void setUp() {
            whitePawn = Pawn.of(Color.WHITE);
        }

        @DisplayName("시작위치가 아닌 경우 2칸 이동할 수 없다")
        @Test
        void canNotMoveTwoStep() {
            //given
            Square whiteSource = Square.of(File.A, Rank.THREE);
            Square whiteTarget = Square.of(File.A, Rank.FIVE);
            Movement whiteMovement = new Movement(whiteSource, whiteTarget);

            //when
            boolean whiteCanMove = whitePawn.canMove(whiteMovement, null);

            //then
            assertThat(whiteCanMove).isFalse();
        }

        @DisplayName("상대 기물이 없을 경우 공격할 수 없다")
        @Test
        void canNotAttack() {
            //given
            Square whiteSource = Square.of(File.A, Rank.TWO);
            Square whiteTarget = Square.of(File.B, Rank.THREE);
            Movement whiteMovement = new Movement(whiteSource, whiteTarget);

            //when
            boolean whiteCanMove = whitePawn.canMove(whiteMovement, null);

            //then
            assertThat(whiteCanMove).isFalse();
        }

        @DisplayName("상대 기물이 바로 앞에 있을 경우 공격할 수 없다")
        @Test
        void canNotAttackOneStep() {
            //given
            Square whiteSource = Square.of(File.A, Rank.TWO);
            Square whiteTarget = Square.of(File.A, Rank.THREE);
            Movement whiteMovement = new Movement(whiteSource, whiteTarget);

            //when
            boolean whiteCanMove = whitePawn.canMove(whiteMovement, Pawn.of(Color.BLACK));

            //then
            assertThat(whiteCanMove).isFalse();
        }
    }
}
