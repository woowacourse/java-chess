package domain.piece;

import domain.Position;
import domain.Side;
import fixture.MovePathFixture;
import fixture.PositionFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static fixture.PositionFixture.C3;
import static fixture.PositionFixture.D3;
import static fixture.PositionFixture.D4;
import static fixture.PositionFixture.D5;
import static fixture.PositionFixture.D6;
import static fixture.PositionFixture.D7;
import static fixture.PositionFixture.E3;
import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @DisplayName("검은색 폰은 특별한 경우가 아니면 한 칸 아래로 움직인다.")
    @Nested
    class blackPawnMove {

        /*
        ........  8
        ........  7
        ........  6
        ........  5
        ...P....  4
        ...*....  3
        ........  2
        ........  1

        abcdefgh
         */
        private static final Position SOURCE = D4;
        private static final List<Position> MOVABLE_POSITIONS = List.of(D3);

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(MOVABLE_POSITIONS);
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(MOVABLE_POSITIONS, SOURCE);
        }

        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Position target) {
            Pawn pawn = new Pawn(Side.BLACK);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

            assertThat(actual).isTrue();
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Position target) {
            Pawn pawn = new Pawn(Side.BLACK);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

            assertThat(actual).isFalse();
        }
    }

    @DisplayName("검은색 폰은 초기화 위치에서 한 칸 혹은 두 칸 아래로 움직인다.")
    @Nested
    class blackPawnMoveAtInitialPosition {

        /*
        ........  8
        ...P....  7
        ...*....  6
        ...*....  5
        ........  4
        ........  3
        ........  2
        ........  1

        abcdefgh
         */
        private static final Position SOURCE = D7;
        private static final List<Position> MOVABLE_POSITIONS = List.of(D6, D5);

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(MOVABLE_POSITIONS);
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(MOVABLE_POSITIONS, SOURCE);
        }

        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Position target) {
            Pawn pawn = new Pawn(Side.BLACK);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

            assertThat(actual).isTrue();
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Position target) {
            Pawn pawn = new Pawn(Side.BLACK);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

            assertThat(actual).isFalse();
        }
    }

    @DisplayName("검은색 폰은 상대 편 기물이 존재하면 아래 대각선 방향으로 움직인다. 한 칸 아래로는 움직일 수 없다.")
    @Nested
    class blackPawnMoveWhenAttack {

        /*
        ........  8
        ........  7
        ........  6
        ........  5
        ...P....  4
        ..ppp...  3
        ........  2
        ........  1

        abcdefgh
         */
        private static final Position SOURCE = D4;
        private static final List<Position> MOVABLE_POSITIONS = List.of(C3, E3);

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(MOVABLE_POSITIONS);
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(MOVABLE_POSITIONS, SOURCE);
        }

        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Position target) {
            Pawn pawn = new Pawn(Side.BLACK);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.hasTargetPiece(new Pawn(Side.WHITE)));

            assertThat(actual).isTrue();
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Position target) {
            Pawn pawn = new Pawn(Side.BLACK);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.hasTargetPiece(new Pawn(Side.WHITE)));

            assertThat(actual).isFalse();
        }
    }
}
