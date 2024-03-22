package domain;

import fixture.PieceFixture;
import fixture.PositionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @DisplayName("폰은 공격할 때는 한 칸 대각선으로 전진한다.")
    @Test
    void canMoveOneSquareDiagonalWhenAttackTest() {
        Pawn pawn = PieceFixture.whitePawn();
        Position current = PositionFixture.a2();
        Position target = PositionFixture.b3();

        Map<Position, Piece> pieces = new LinkedHashMap<>() {{
            put(target, PieceFixture.blackPawn());
        }};

        boolean actual = pawn.canMove(current, target, pieces);

        assertThat(actual).isTrue();
    }

    @DisplayName("폰은 공격할 때는 두 칸 이상 대각선으로 전진할 수 없다.")
    @Test
    void cantMoveTwoSquareDiagonalWhenAttackTest() {
        Pawn pawn = PieceFixture.whitePawn();
        Position current = PositionFixture.a2();
        Position target = PositionFixture.c4();

        Map<Position, Piece> pieces = new LinkedHashMap<>() {{
            put(target, PieceFixture.blackPawn());
        }};

        boolean actual = pawn.canMove(current, target, pieces);

        assertThat(actual).isFalse();
    }

    @DisplayName("폰은 한 칸 전진으로 공격할 수 없다.")
    @Test
    void cantMoveOneSquareForwardWhenAttackTest() {
        Pawn pawn = PieceFixture.whitePawn();
        Position current = PositionFixture.a2();
        Position target = PositionFixture.a3();

        Map<Position, Piece> pieces = new LinkedHashMap<>() {{
            put(target, PieceFixture.blackPawn());
        }};

        boolean actual = pawn.canMove(current, target, pieces);

        assertThat(actual).isFalse();
    }

    @DisplayName("폰은 두 칸 전진으로 공격할 수 없다.")
    @Test
    void cantMoveTwoSquareForwardWhenAttackTest() {
        Pawn pawn = PieceFixture.whitePawn();
        Position current = PositionFixture.a2();
        Position target = PositionFixture.a4();

        Map<Position, Piece> pieces = new LinkedHashMap<>() {{
            put(target, PieceFixture.blackPawn());
        }};

        boolean actual = pawn.canMove(current, target, pieces);

        assertThat(actual).isFalse();
    }

    @DisplayName("폰은 초기화 위치 외에서는 한 칸 전진한다.")
    @Test
    void canMoveForwardOneSquareTest() {
        Pawn pawn = PieceFixture.whitePawn();
        Position current = PositionFixture.a3();
        Position target = PositionFixture.a4();

        boolean actual = pawn.canMove(current, target, new LinkedHashMap<>());

        assertThat(actual).isTrue();
    }

    @DisplayName("폰은 초기화 위치 외에서는 두 칸 이상 전진할 수 없다.")
    @Test
    void cantMoveForwardTwoSquareTest() {
        Pawn pawn = PieceFixture.whitePawn();
        Position current = PositionFixture.a3();
        Position target = PositionFixture.a5();

        boolean actual = pawn.canMove(current, target, new LinkedHashMap<>());

        assertThat(actual).isFalse();
    }

    @Nested
    @DisplayName("폰은 초기화 위치에서는 한 칸 혹은 두 칸 앞으로 전진한다.")
    class CanMoveAtInitPositionTest {

        @DisplayName("폰은 초기화 위치에서 한 칸 앞으로 전진한다.")
        @Test
        void canMoveOneSquareForwardTest() {
            Pawn pawn = PieceFixture.whitePawn();
            Position current = PositionFixture.a2();
            Position target = PositionFixture.a3();

            boolean actual = pawn.canMove(current, target, new LinkedHashMap<>());

            assertThat(actual).isTrue();
        }

        @DisplayName("폰은 초기화 위치에서 두 칸 앞으로 전진한다.")
        @Test
        void canMoveTwoSquareForwardTest() {
            Pawn pawn = PieceFixture.whitePawn();
            Position current = PositionFixture.a2();
            Position target = PositionFixture.a4();

            boolean actual = pawn.canMove(current, target, new LinkedHashMap<>());

            assertThat(actual).isTrue();
        }
    }

    @DisplayName("폰은 수평, 대각선, 후진 방향으로 움직일 수 없다.")
    @Nested
    class CantMoveAtInitPositionTest {

        private static Stream<Arguments> NonReversiblePosition() {
            return Stream.of(
                    Arguments.arguments(PieceFixture.blackPawn(), PositionFixture.a7(), PositionFixture.a8()),
                    Arguments.arguments(PieceFixture.whitePawn(), PositionFixture.a2(), PositionFixture.a1())
            );
        }

        @DisplayName("폰은 수평 방향으로 움직일 수 없다.")
        @Test
        void cantMoveSideTest() {
            Pawn pawn = PieceFixture.whitePawn();
            Position current = PositionFixture.a2();
            Position target = PositionFixture.b2();

            boolean actual = pawn.canMove(current, target, new LinkedHashMap<>());

            assertThat(actual).isFalse();
        }

        @DisplayName("폰은 후진할 수 없다.")
        @ParameterizedTest
        @MethodSource("NonReversiblePosition")
        void cantReverseMoveTest(Pawn pawn, Position current, Position target) {

            boolean actual = pawn.canMove(current, target, new LinkedHashMap<>());

            assertThat(actual).isFalse();
        }

        @DisplayName("폰은 공격할 때가 아니면 대각선 방향으로 움직일 수 없다.")
        @Test
        void cantMoveDiagonalTest() {
            Pawn pawn = PieceFixture.whitePawn();
            Position current = PositionFixture.a2();
            Position target = PositionFixture.b3();

            boolean actual = pawn.canMove(current, target, new LinkedHashMap<>());

            assertThat(actual).isFalse();
        }
    }
}
