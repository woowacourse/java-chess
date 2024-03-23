package domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.piece.piecerole.Bishop;
import domain.piece.piecerole.King;
import domain.piece.piecerole.Knight;
import domain.piece.piecerole.Pawn;
import domain.piece.piecerole.PieceRole;
import domain.piece.piecerole.Queen;
import domain.piece.piecerole.Rook;
import domain.position.Position;
import fixture.PositionFixture;
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
            Position sourcePosition = PositionFixture.generateB1Position();
            Position targetPosition = PositionFixture.generateB2Position();

            PieceRole king = King.from();

            assertDoesNotThrow(() -> king.validateMovableRoute(sourcePosition, targetPosition, new HashMap<>()));
        }

        @DisplayName("퀸이 (b,1)에서 (b,7)로 이동한다.")
        @Test
        void canQueenMove() {
            Position sourcePosition = PositionFixture.generateB1Position();
            Position targetPosition = PositionFixture.generateB7Position();

            PieceRole queen = Queen.from();

            assertDoesNotThrow(() -> queen.validateMovableRoute(sourcePosition, targetPosition, new HashMap<>()));
        }

        @DisplayName("룩이 (b,1)에서 (b,7)로 이동한다.")
        @Test
        void canRookMove() {
            Position sourcePosition = PositionFixture.generateB1Position();
            Position targetPosition = PositionFixture.generateB7Position();

            PieceRole rook = Rook.from();

            assertDoesNotThrow(() -> rook.validateMovableRoute(sourcePosition, targetPosition, new HashMap<>()));
        }

        @DisplayName("나이트가 (b,1)에서 (c,3)로 이동한다.")
        @Test
        void canKnightMove() {
            Position sourcePosition = PositionFixture.generateB1Position();
            Position targetPosition = PositionFixture.generateC3Position();

            PieceRole knight = Knight.from();

            assertDoesNotThrow(() -> knight.validateMovableRoute(sourcePosition, targetPosition, new HashMap<>()));
        }

        @DisplayName("비숍이 (b,1)에서 (c,2)로 이동한다.")
        @Test
        void canBishopMove() {
            Position sourcePosition = PositionFixture.generateB1Position();
            Position targetPosition = PositionFixture.generateC2Position();

            PieceRole bishop = Bishop.from();

            assertDoesNotThrow(() -> bishop.validateMovableRoute(sourcePosition, targetPosition, new HashMap<>()));
        }

        @DisplayName("흰색 폰이 (c,2)에서 (c,3)로 이동한다.")
        @Test
        void canPawnMove() {
            Position sourcePosition = PositionFixture.generateC2Position();
            Position targetPosition = PositionFixture.generateC3Position();

            PieceRole pawn = Pawn.from(Color.WHITE);

            assertDoesNotThrow(() -> pawn.validateMovableRoute(sourcePosition, targetPosition, new HashMap<>()));
        }
    }

    @DisplayName("이동 실패 테스트")
    @Nested
    class FailTest {
        @DisplayName("킹이 (b,1)에서 (c,2)로 이동하지 못한다.")
        @Test
        void cannotKingMove() {
            Position sourcePosition = PositionFixture.generateB1Position();
            Position targetPosition = PositionFixture.generateC2Position();

            PieceRole king = King.from();

            Assertions.assertThatThrownBy(
                    () -> king.validateMovableRoute(sourcePosition, targetPosition, new HashMap<>())
            ).isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("퀸이 (b,1)에서 (c,3)로 이동하지 못한다.")
        @Test
        void cannotQueenMove() {
            Position sourcePosition = PositionFixture.generateB1Position();
            Position targetPosition = PositionFixture.generateC3Position();

            PieceRole queen = Queen.from();

            Assertions.assertThatThrownBy(
                    () -> queen.validateMovableRoute(sourcePosition, targetPosition, new HashMap<>())
            ).isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("룩이 (c,1)에서 (b,2)로 이동하지 못한다.")
        @Test
        void cannotRookMove() {
            Position sourcePosition = PositionFixture.generateC1Position();
            Position targetPosition = PositionFixture.generateB2Position();

            PieceRole rook = Rook.from();

            Assertions.assertThatThrownBy(
                    () -> rook.validateMovableRoute(sourcePosition, targetPosition, new HashMap<>())
            ).isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("나이트가 (b,1)에서 (b,2)로 이동하지 못한다.")
        @Test
        void cannotKnightMove() {
            Position sourcePosition = PositionFixture.generateB1Position();
            Position targetPosition = PositionFixture.generateB2Position();

            PieceRole knight = Knight.from();

            Assertions.assertThatThrownBy(
                    () -> knight.validateMovableRoute(sourcePosition, targetPosition, new HashMap<>())
            ).isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("비숍이 (b,1)에서 (b,2)로 이동하지 못한다.")
        @Test
        void cannotBishopMove() {
            Position sourcePosition = PositionFixture.generateB1Position();
            Position targetPosition = PositionFixture.generateB2Position();

            PieceRole bishop = Bishop.from();

            Assertions.assertThatThrownBy(
                    () -> bishop.validateMovableRoute(sourcePosition, targetPosition, new HashMap<>())
            ).isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("검은색 폰이 (c,2)에서 (c,3)로 이동하지 못한다.")
        @Test
        void canPawnMove() {
            Position sourcePosition = PositionFixture.generateC2Position();
            Position targetPosition = PositionFixture.generateC3Position();

            PieceRole pawn = Pawn.from(Color.BLACK);

            Assertions.assertThatThrownBy(
                    () -> pawn.validateMovableRoute(sourcePosition, targetPosition, new HashMap<>())
            ).isInstanceOf(IllegalArgumentException.class);
        }
    }

}
