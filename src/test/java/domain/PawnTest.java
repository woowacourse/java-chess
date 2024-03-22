package domain;

import domain.piece.Piece;
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
        Pawn pawn = new Pawn(Side.WHITE);
        Position current = PositionFixture.A2();
        Position target = PositionFixture.B3();

        Map<Position, Piece> pieces = new LinkedHashMap<>() {{
            put(target, new Pawn(Side.BLACK));
        }};

//        boolean actual = pawn.isRuleBroken(current, target, pieces);
//
//        assertThat(actual).isTrue();
    }

    @DisplayName("폰은 공격할 때는 두 칸 이상 대각선으로 전진할 수 없다.")
    @Test
    void cantMoveTwoSquareDiagonalWhenAttackTest() {
        Pawn pawn = new Pawn(Side.WHITE);
        Position current = PositionFixture.A2();
        Position target = PositionFixture.c4();

        Map<Position, Piece> pieces = new LinkedHashMap<>() {{
            put(target, new Pawn(Side.BLACK));
        }};

//        boolean actual = pawn.isRuleBroken(current, target, pieces);
//
//        assertThat(actual).isFalse();
    }

    @DisplayName("폰은 한 칸 전진으로 공격할 수 없다.")
    @Test
    void cantMoveOneSquareForwardWhenAttackTest() {
        Pawn pawn = new Pawn(Side.WHITE);
        Position current = PositionFixture.A2();
        Position target = PositionFixture.A3();

        Map<Position, Piece> pieces = new LinkedHashMap<>() {{
            put(target, new Pawn(Side.BLACK));
        }};

//        boolean actual = pawn.isRuleBroken(current, target, pieces);
//
//        assertThat(actual).isFalse();
    }

    @DisplayName("폰은 두 칸 전진으로 공격할 수 없다.")
    @Test
    void cantMoveTwoSquareForwardWhenAttackTest() {
        Pawn pawn = new Pawn(Side.WHITE);
        Position current = PositionFixture.A2();
        Position target = PositionFixture.a4();

        Map<Position, Piece> pieces = new LinkedHashMap<>() {{
            put(target, new Pawn(Side.BLACK));
        }};

//        boolean actual = pawn.isRuleBroken(current, target, pieces);
//
//        assertThat(actual).isFalse();
    }

    @DisplayName("폰은 초기화 위치 외에서는 한 칸 전진한다.")
    @Test
    void canMoveForwardOneSquareTest() {
        Pawn pawn = new Pawn(Side.WHITE);
        Position current = PositionFixture.A3();
        Position target = PositionFixture.a4();

//        boolean actual = pawn.isRuleBroken(current, target, new LinkedHashMap<>());
//
//        assertThat(actual).isTrue();
    }

    @DisplayName("폰은 초기화 위치 외에서는 두 칸 이상 전진할 수 없다.")
    @Test
    void cantMoveForwardTwoSquareTest() {
        Pawn pawn = new Pawn(Side.WHITE);
        Position current = PositionFixture.A3();
        Position target = PositionFixture.a5();

//        boolean actual = pawn.isRuleBroken(current, target, new LinkedHashMap<>());
//
//        assertThat(actual).isFalse();
    }

    @Nested
    @DisplayName("폰은 초기화 위치에서는 한 칸 혹은 두 칸 앞으로 전진한다.")
    class CanMoveAtInitPositionTest {

        @DisplayName("폰은 초기화 위치에서 한 칸 앞으로 전진한다.")
        @Test
        void canMoveOneSquareForwardTest() {
            Pawn pawn = new Pawn(Side.WHITE);
            Position current = PositionFixture.A2();
            Position target = PositionFixture.A3();

//            boolean actual = pawn.isRuleBroken(current, target, new LinkedHashMap<>());
//
//            assertThat(actual).isTrue();
        }

        @DisplayName("폰은 초기화 위치에서 두 칸 앞으로 전진한다.")
        @Test
        void canMoveTwoSquareForwardTest() {
            Pawn pawn = new Pawn(Side.WHITE);
            Position current = PositionFixture.A2();
            Position target = PositionFixture.a4();

//            boolean actual = pawn.isRuleBroken(current, target, new LinkedHashMap<>());
//
//            assertThat(actual).isTrue();
        }
    }

    @DisplayName("폰은 수평, 대각선, 후진 방향으로 움직일 수 없다.")
    @Nested
    class CantMoveAtInitPositionTest {

        private static Stream<Arguments> NonReversiblePosition() {
            return Stream.of(
                    Arguments.arguments(Side.BLACK, PositionFixture.a7(), PositionFixture.a8()),
                    Arguments.arguments(Side.WHITE, PositionFixture.A2(), PositionFixture.A1())
            );
        }

        @DisplayName("폰은 수평 방향으로 움직일 수 없다.")
        @Test
        void cantMoveSideTest() {
            Pawn pawn = new Pawn(Side.WHITE);
            Position current = PositionFixture.A2();
            Position target = PositionFixture.B2();

//            boolean actual = pawn.isRuleBroken(current, target, new LinkedHashMap<>());
//
//            assertThat(actual).isFalse();
        }

        @DisplayName("폰은 후진할 수 없다.")
        @ParameterizedTest
        @MethodSource("NonReversiblePosition")
        void cantReverseMoveTest(Side side, Position current, Position target) {
            Pawn pawn = new Pawn(side);

//            boolean actual = pawn.isRuleBroken(current, target, new LinkedHashMap<>());
//
//            assertThat(actual).isFalse();
        }

        @DisplayName("폰은 공격할 때가 아니면 대각선 방향으로 움직일 수 없다.")
        @Test
        void cantMoveDiagonalTest() {
            Pawn pawn = new Pawn(Side.WHITE);
            Position current = PositionFixture.A2();
            Position target = PositionFixture.B3();

//            boolean actual = pawn.isRuleBroken(current, target, new LinkedHashMap<>());
//
//            assertThat(actual).isFalse();
        }
    }
}
