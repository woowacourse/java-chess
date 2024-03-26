package domain.piece;

import static fixture.PositionFixture.*;

import domain.piece.piecerole.Bishop;
import domain.piece.piecerole.BlackPawn;
import domain.piece.piecerole.King;
import domain.piece.piecerole.Knight;
import domain.piece.piecerole.PieceRole;
import domain.piece.piecerole.Queen;
import domain.piece.piecerole.Rook;
import domain.piece.piecerole.WhitePawn;
import domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PieceRoleTest {
    @DisplayName("이동 성공 테스트")
    @Nested
    class SuccessTest {
        @DisplayName("킹이 (b,1)에서 (b,2)로 이동한다.")
        @Test
        void canKingMove() {
            Position sourcePosition = createB1();
            Position targetPosition = createB2();

            PieceRole king = new King();

            Assertions.assertThat(king.canMove(sourcePosition, targetPosition)).isTrue();
        }

        @DisplayName("퀸이 (b,1)에서 (b,7)로 이동한다.")
        @Test
        void canQueenMove() {
            Position sourcePosition = createB1();
            Position targetPosition = createB7();

            PieceRole queen = new Queen();

            Assertions.assertThat(queen.canMove(sourcePosition, targetPosition)).isTrue();
        }

        @DisplayName("룩이 (b,1)에서 (b,7)로 이동한다.")
        @Test
        void canRookMove() {
            Position sourcePosition = createB1();
            Position targetPosition = createB7();

            PieceRole rook = new Rook();

            Assertions.assertThat(rook.canMove(sourcePosition, targetPosition)).isTrue();
        }

        @DisplayName("나이트가 (b,1)에서 (c,3)로 이동한다.")
        @Test
        void canKnightMove() {
            Position sourcePosition = createB1();
            Position targetPosition = createC3();

            PieceRole knight = new Knight();

            Assertions.assertThat(knight.canMove(sourcePosition, targetPosition)).isTrue();
        }

        @DisplayName("비숍이 (b,1)에서 (c,2)로 이동한다.")
        @Test
        void canBishopMove() {
            Position sourcePosition = createB1();
            Position targetPosition = createC2();

            PieceRole bishop = new Bishop();

            Assertions.assertThat(bishop.canMove(sourcePosition, targetPosition)).isTrue();
        }

        @DisplayName("흰색 폰이 (c,2)에서 (c,3)로 이동한다.")
        @Test
        void canPawnMove() {
            Position sourcePosition = createC2();
            Position targetPosition = createC3();

            PieceRole pawn = new WhitePawn();

            Assertions.assertThat(pawn.canMove(sourcePosition, targetPosition)).isTrue();
        }
    }

    @DisplayName("이동 실패 테스트")
    @Nested
    class FailTest {
        @DisplayName("킹이 (b,1)에서 (c,2)로 이동하지 못한다.")
        @Test
        void cannotKingMove() {
            Position sourcePosition = createB1();
            Position targetPosition = createC2();

            PieceRole king = new King();

            Assertions.assertThat(king.canMove(sourcePosition, targetPosition)).isFalse();
        }

        @DisplayName("퀸이 (b,1)에서 (c,3)로 이동하지 못한다.")
        @Test
        void cannotQueenMove() {
            Position sourcePosition = createB1();
            Position targetPosition = createC3();

            PieceRole queen = new Queen();

            Assertions.assertThat(queen.canMove(sourcePosition, targetPosition)).isFalse();
        }

        @DisplayName("룩이 (c,1)에서 (b,2)로 이동하지 못한다.")
        @Test
        void cannotRookMove() {
            Position sourcePosition = createC1();
            Position targetPosition = createB2();

            PieceRole rook = new Rook();

            Assertions.assertThat(rook.canMove(sourcePosition, targetPosition)).isFalse();
        }

        @DisplayName("나이트가 (b,1)에서 (b,2)로 이동하지 못한다.")
        @Test
        void cannotKnightMove() {
            Position sourcePosition = createB1();
            Position targetPosition = createB2();

            PieceRole knight = new Knight();

            Assertions.assertThat(knight.canMove(sourcePosition, targetPosition)).isFalse();
        }

        @DisplayName("비숍이 (b,1)에서 (b,2)로 이동하지 못한다.")
        @Test
        void cannotBishopMove() {
            Position sourcePosition = createB1();
            Position targetPosition = createB2();

            PieceRole bishop = new Bishop();

            Assertions.assertThat(bishop.canMove(sourcePosition, targetPosition)).isFalse();
        }

        @DisplayName("검은색 폰이 (c,2)에서 (c,3)로 이동하지 못한다.")
        @Test
        void canPawnMove() {
            Position sourcePosition = createC2();
            Position targetPosition = createC3();

            PieceRole pawn = new BlackPawn();

            Assertions.assertThat(pawn.canMove(sourcePosition, targetPosition)).isFalse();
        }
    }

}
