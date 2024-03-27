package domain.piece;

import static fixture.PositionFixture.b1;
import static fixture.PositionFixture.b2;
import static fixture.PositionFixture.b7;
import static fixture.PositionFixture.c1;
import static fixture.PositionFixture.c2;
import static fixture.PositionFixture.c3;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.piece.piecerole.Bishop;
import domain.piece.piecerole.BlackPawn;
import domain.piece.piecerole.King;
import domain.piece.piecerole.Knight;
import domain.piece.piecerole.PieceRole;
import domain.piece.piecerole.Queen;
import domain.piece.piecerole.Rook;
import domain.piece.piecerole.WhitePawn;
import java.util.HashMap;
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
            PieceRole king = King.create();

            assertDoesNotThrow(() -> king.validateMovableRoute(b1(), b2(), new HashMap<>()));
        }

        @DisplayName("퀸이 (b,1)에서 (b,7)로 이동한다.")
        @Test
        void canQueenMove() {
            PieceRole queen = Queen.create();

            assertDoesNotThrow(() -> queen.validateMovableRoute(b1(), b7(), new HashMap<>()));
        }

        @DisplayName("룩이 (b,1)에서 (b,7)로 이동한다.")
        @Test
        void canRookMove() {
            PieceRole rook = Rook.create();

            assertDoesNotThrow(() -> rook.validateMovableRoute(b1(), b7(), new HashMap<>()));
        }

        @DisplayName("나이트가 (b,1)에서 (c,3)로 이동한다.")
        @Test
        void canKnightMove() {
            PieceRole knight = Knight.create();

            assertDoesNotThrow(() -> knight.validateMovableRoute(b1(), c3(), new HashMap<>()));
        }

        @DisplayName("비숍이 (b,1)에서 (c,2)로 이동한다.")
        @Test
        void canBishopMove() {
            PieceRole bishop = Bishop.create();

            assertDoesNotThrow(() -> bishop.validateMovableRoute(b1(), c2(), new HashMap<>()));
        }

        @DisplayName("흰색 폰이 (c,2)에서 (c,3)로 이동한다.")
        @Test
        void canPawnMove() {
            PieceRole pawn = WhitePawn.create();

            assertDoesNotThrow(() -> pawn.validateMovableRoute(c2(), c3(), new HashMap<>()));
        }
    }

    @DisplayName("이동 실패 테스트")
    @Nested
    class FailTest {
        @DisplayName("킹이 (b,1)에서 (c,2)로 이동하지 못한다.")
        @Test
        void cannotKingMove() {
            PieceRole king = King.create();

            Assertions.assertThatThrownBy(
                    () -> king.validateMovableRoute(b1(), c2(), new HashMap<>())
            ).isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("퀸이 (b,1)에서 (c,3)로 이동하지 못한다.")
        @Test
        void cannotQueenMove() {
            PieceRole queen = Queen.create();

            Assertions.assertThatThrownBy(
                    () -> queen.validateMovableRoute(b1(), c3(), new HashMap<>())
            ).isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("룩이 (c,1)에서 (b,2)로 이동하지 못한다.")
        @Test
        void cannotRookMove() {
            PieceRole rook = Rook.create();

            Assertions.assertThatThrownBy(
                    () -> rook.validateMovableRoute(c1(), b2(), new HashMap<>())
            ).isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("나이트가 (b,1)에서 (b,2)로 이동하지 못한다.")
        @Test
        void cannotKnightMove() {
            PieceRole knight = Knight.create();

            Assertions.assertThatThrownBy(
                    () -> knight.validateMovableRoute(b1(), b2(), new HashMap<>())
            ).isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("비숍이 (b,1)에서 (b,2)로 이동하지 못한다.")
        @Test
        void cannotBishopMove() {
            PieceRole bishop = Bishop.create();

            Assertions.assertThatThrownBy(
                    () -> bishop.validateMovableRoute(b1(), b2(), new HashMap<>())
            ).isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("검은색 폰이 (c,2)에서 (c,3)로 이동하지 못한다.")
        @Test
        void canPawnMove() {
            PieceRole pawn = BlackPawn.create();

            Assertions.assertThatThrownBy(
                    () -> pawn.validateMovableRoute(c2(), c3(), new HashMap<>())
            ).isInstanceOf(IllegalArgumentException.class);
        }
    }

}
