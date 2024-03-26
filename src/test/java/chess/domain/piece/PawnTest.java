package chess.domain.piece;

import chess.domain.position.Position;
import chess.fixture.PositionFixture;
import chess.fixture.MovePathFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.fixture.PieceFixture.BLACK_PAWN;
import static chess.fixture.PieceFixture.WHITE_PAWN;
import static chess.fixture.PositionFixture.C3;
import static chess.fixture.PositionFixture.C5;
import static chess.fixture.PositionFixture.D2;
import static chess.fixture.PositionFixture.D3;
import static chess.fixture.PositionFixture.D4;
import static chess.fixture.PositionFixture.D5;
import static chess.fixture.PositionFixture.D6;
import static chess.fixture.PositionFixture.D7;
import static chess.fixture.PositionFixture.E3;
import static chess.fixture.PositionFixture.E5;
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
        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Position target) {
            boolean actual = BLACK_PAWN.hasFollowedRule(D4, target, MovePathFixture.noPieces());

            assertThat(actual).isTrue();
        }

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(List.of(D3));
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Position target) {
            boolean actual = BLACK_PAWN.hasFollowedRule(D4, target, MovePathFixture.noPieces());

            assertThat(actual).isFalse();
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(List.of(D3), D4);
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
        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Position target) {
            boolean actual = BLACK_PAWN.hasFollowedRule(D7, target, MovePathFixture.noPieces());

            assertThat(actual).isTrue();
        }

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(List.of(D6, D5));
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Position target) {
            boolean actual = BLACK_PAWN.hasFollowedRule(D7, target, MovePathFixture.noPieces());

            assertThat(actual).isFalse();
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(List.of(D6, D5), D7);
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
        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Position target) {
            boolean actual = BLACK_PAWN.hasFollowedRule(D4, target, MovePathFixture.hasTargetPiece(WHITE_PAWN));

            assertThat(actual).isTrue();
        }

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(List.of(C3, E3));
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Position target) {
            boolean actual = BLACK_PAWN.hasFollowedRule(D4, target, MovePathFixture.hasTargetPiece(WHITE_PAWN));

            assertThat(actual).isFalse();
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(List.of(C3, E3), D4);
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
        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Position target) {
            boolean actual = WHITE_PAWN.hasFollowedRule(D4, target, MovePathFixture.noPieces());

            assertThat(actual).isTrue();
        }

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(List.of(D5));
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Position target) {
            boolean actual = WHITE_PAWN.hasFollowedRule(D4, target, MovePathFixture.noPieces());

            assertThat(actual).isFalse();
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(List.of(D5), D4);
        }
    }

    @DisplayName("흰색 폰은 초기화 위치에서 한 칸 혹은 두 칸 위로 움직인다.")
    @Nested
    class whitePawnMoveAtInitialPosition {

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
        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Position target) {
            boolean actual = WHITE_PAWN.hasFollowedRule(D2, target, MovePathFixture.noPieces());

            assertThat(actual).isTrue();
        }

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(List.of(D3, D4));
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Position target) {
            boolean actual = WHITE_PAWN.hasFollowedRule(D2, target, MovePathFixture.noPieces());

            assertThat(actual).isFalse();
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(List.of(D3, D4), D2);
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
        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Position target) {
            boolean actual = WHITE_PAWN.hasFollowedRule(D4, target, MovePathFixture.hasTargetPiece(BLACK_PAWN));

            assertThat(actual).isTrue();
        }

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(List.of(C5, E5));
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Position target) {
            boolean actual = WHITE_PAWN.hasFollowedRule(D4, target, MovePathFixture.hasTargetPiece(BLACK_PAWN));

            assertThat(actual).isFalse();
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(List.of(C5, E5), D4);
        }
    }
}
