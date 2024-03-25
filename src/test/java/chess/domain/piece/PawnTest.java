package chess.domain.piece;

import chess.domain.square.Square;
import chess.fixture.PositionFixture;
import chess.fixture.MovePathFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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
        private static final Square SOURCE = PositionFixture.D4;
        private static final List<Square> MOVABLE_SQUARES = List.of(PositionFixture.D3);

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(MOVABLE_SQUARES);
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(MOVABLE_SQUARES, SOURCE);
        }

        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Square target) {
            Pawn pawn = new Pawn(Side.BLACK);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

            assertThat(actual).isTrue();
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Square target) {
            Pawn pawn = new Pawn(Side.BLACK);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

            assertThat(actual).isFalse();
        }
    }

    @DisplayName("검은색 폰은 초기화 위치에서 한 칸 혹은 두 칸 아래로 움직인다.")
    @Nested
    class blackPawnMoveAtInitialSquare {

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
        private static final Square SOURCE = PositionFixture.D7;
        private static final List<Square> MOVABLE_SQUARES = List.of(PositionFixture.D6, PositionFixture.D5);

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(MOVABLE_SQUARES);
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(MOVABLE_SQUARES, SOURCE);
        }

        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Square target) {
            Pawn pawn = new Pawn(Side.BLACK);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

            assertThat(actual).isTrue();
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Square target) {
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
        private static final Square SOURCE = PositionFixture.D4;
        private static final List<Square> MOVABLE_SQUARES = List.of(PositionFixture.C3, PositionFixture.E3);

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(MOVABLE_SQUARES);
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(MOVABLE_SQUARES, SOURCE);
        }

        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Square target) {
            Pawn pawn = new Pawn(Side.BLACK);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.hasTargetPiece(new Pawn(Side.WHITE)));

            assertThat(actual).isTrue();
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Square target) {
            Pawn pawn = new Pawn(Side.BLACK);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.hasTargetPiece(new Pawn(Side.WHITE)));

            assertThat(actual).isFalse();
        }
    }

    @DisplayName("흰색 폰은 특별한 경우가 아니면 한 칸 위로 움직인다.")
    @Nested
    class whitePawnMove {

        /*
        ........  8
        ........  7
        ........  6
        ...*....  5
        ...p....  4
        ........  3
        ........  2
        ........  1

        abcdefgh
         */
        private static final Square SOURCE = PositionFixture.D4;
        private static final List<Square> MOVABLE_SQUARES = List.of(PositionFixture.D5);

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(MOVABLE_SQUARES);
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(MOVABLE_SQUARES, SOURCE);
        }

        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Square target) {
            Pawn pawn = new Pawn(Side.WHITE);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

            assertThat(actual).isTrue();
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Square target) {
            Pawn pawn = new Pawn(Side.WHITE);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

            assertThat(actual).isFalse();
        }
    }

    @DisplayName("흰색 폰은 초기화 위치에서 한 칸 혹은 두 칸 위로 움직인다.")
    @Nested
    class whitePawnMoveAtInitialSquare {

        /*
        ........  8
        ........  7
        ........  6
        ........  5
        ...*....  4
        ...*....  3
        ...p....  2
        ........  1

        abcdefgh
         */
        private static final Square SOURCE = PositionFixture.D2;
        private static final List<Square> MOVABLE_SQUARES = List.of(PositionFixture.D3, PositionFixture.D4);

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(MOVABLE_SQUARES);
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(MOVABLE_SQUARES, SOURCE);
        }

        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Square target) {
            Pawn pawn = new Pawn(Side.WHITE);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

            assertThat(actual).isTrue();
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Square target) {
            Pawn pawn = new Pawn(Side.WHITE);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.noPieces());

            assertThat(actual).isFalse();
        }
    }

    @DisplayName("흰색 폰은 상대 편 기물이 존재하면 위 대각선 방향으로 움직인다. 한 칸 위로는 움직일 수 없다.")
    @Nested
    class whitePawnMoveWhenAttack {

        /*
        ........  8
        ........  7
        ........  6
        ..PPP...  5
        ...p....  4
        ........  3
        ........  2
        ........  1

        abcdefgh
         */
        private static final Square SOURCE = PositionFixture.D4;
        private static final List<Square> MOVABLE_SQUARES = List.of(PositionFixture.C5, PositionFixture.E5);

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(MOVABLE_SQUARES);
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(MOVABLE_SQUARES, SOURCE);
        }

        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Square target) {
            Pawn pawn = new Pawn(Side.WHITE);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.hasTargetPiece(new Pawn(Side.BLACK)));

            assertThat(actual).isTrue();
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Square target) {
            Pawn pawn = new Pawn(Side.WHITE);

            boolean actual = pawn.hasFollowedRule(SOURCE, target, MovePathFixture.hasTargetPiece(new Pawn(Side.BLACK)));

            assertThat(actual).isFalse();
        }
    }
}
