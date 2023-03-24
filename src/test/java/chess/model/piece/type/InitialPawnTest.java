package chess.model.piece.type;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.piece.Camp;
import chess.model.piece.Piece;
import chess.model.position.Distance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class InitialPawnTest {

    @Test
    @DisplayName("pick()은 호출하면 가지고 있던 pawn을 반환한다")
    void pick_whenCall_thenReturnThis() {
        // given
        final Piece pawn = new Pawn(Camp.BLACK);
        final InitialPawn blackInitialPawn = new InitialPawn(pawn);

        // when
        final Piece actual = blackInitialPawn.pick();

        // then
        assertThat(actual).isSameAs(pawn);
    }

    @Test
    @DisplayName("isNotPassable()은 호출하면 true를 반환한다")
    void isNotPassable_whenCall_thenReturnFalse() {
        // given
        final Piece pawn = new Pawn(Camp.BLACK);
        final InitialPawn blackInitialPawn = new InitialPawn(pawn);

        // when
        final boolean actual = blackInitialPawn.isNotPassable();

        // then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest(name = "검은색 진영 폰은 isSameTeam()을 호출할 때 Camp.{0}을 건네주면 {1}을 반환한다")
    @DisplayName("isSameTeam() 테스트")
    @MethodSource("chess.helper.arguments.CampArguments#provideIsSameTeamByBlack")
    void isSameTeam_givenCamp_thenReturnIsSameTeam(final Camp camp, final boolean expected) {
        // given
        final Piece pawn = new Pawn(Camp.BLACK);
        final InitialPawn blackInitialPawn = new InitialPawn(pawn);

        // when
        final boolean actual = blackInitialPawn.isSameTeam(camp);

        // then
        assertThat(actual).isSameAs(expected);
    }

    @Test
    @DisplayName("isKing()은 호출하면 false를 반환한다")
    void isKing_whenCall_thenReturnFalse() {
        // given
        final Piece pawn = new Pawn(Camp.BLACK);
        final InitialPawn blackInitialPawn = new InitialPawn(pawn);

        // when
        final boolean actual = blackInitialPawn.isKing();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("isPawn()은 호출하면 true를 반환한다")
    void ispawn_whenCall_thenReturnFalse() {
        // given
        final Piece pawn = new Pawn(Camp.BLACK);
        final InitialPawn blackInitialPawn = new InitialPawn(pawn);

        // when
        final boolean actual = blackInitialPawn.isPawn();

        // then
        assertThat(actual).isTrue();
    }

    @Nested
    @DisplayName("movable() 검은색 폰 테스트")
    class BlackInitialPawnMovableMethodTest {

        private final Piece empty = Empty.EMPTY_PIECE;
        private Piece blackInitialPawn;
        private Piece ally;
        private Piece enemy;
        
        @BeforeEach
        void beforeEach() {
            final Pawn blackPawn = new Pawn(Camp.BLACK);
            
            blackInitialPawn = new InitialPawn(blackPawn);
            ally = new InitialPawn(blackInitialPawn);
            
            final Pawn whiteInitialPawn = new Pawn(Camp.WHITE);
            
            enemy = new InitialPawn(whiteInitialPawn);
        }

        @ParameterizedTest(name = "목적지가 적군인 경우 움직이는 방향이 대각선 ({0},{1}) 일 때 움직일 수 있다.")
        @DisplayName("movable() 유효한 공격 이동 방향, 유효한 공격 이동 거리, 적군 테스트")
        @CsvSource(value = {"1:-1", "-1:-1"}, delimiter = ':')
        void movable_givenValidAttackDistanceAndEnemyTarget_thenReturnTrue(final int file, final int rank) {
            // given
            final Distance distance = new Distance(file, rank);

            // when
            final boolean actual = blackInitialPawn.movable(distance, enemy);

            // then
            assertThat(actual).isTrue();
        }

        @ParameterizedTest(name = "목적지가 빈 곳이거나 아군인 경우 움직이는 방향이 대각선 ({0},{1}) 일 때 움직일 수 없다.")
        @DisplayName("movable() 유효한 공격 이동 방향, 유효한 공격 이동 거리, 빈 곳 테스트")
        @CsvSource(value = {"1:-1", "-1:-1"}, delimiter = ':')
        void movable_givenValidAttackDistanceAndEmptyTarget_thenReturnFalse(final int file, final int rank) {
            // given
            final Distance distance = new Distance(file, rank);

            // when
            final boolean actual = blackInitialPawn.movable(distance, ally);

            // then
            assertThat(actual).isFalse();
        }

        @ParameterizedTest(name = "목적지가 빈 곳인 경우 직진({0}, {1})으로 움직일 수 있다.")
        @DisplayName("movable() 성공 테스트")
        @CsvSource(value = {"0:-1", "0:-2"}, delimiter = ':')
        void movable_givenValidDistanceAndEmptyTarget_thenReturnFalse(final int file, final int rank) {
            // given
            final Distance distance = new Distance(file, rank);

            // when
            final boolean actual = blackInitialPawn.movable(distance, empty);

            // then
            assertThat(actual).isTrue();
        }

        @ParameterizedTest(name = "목적지가 적군이거나 아군일 경우 직진({0}, {1})으로 움직일 수 없다.")
        @DisplayName("movable() 실패 테스트")
        @CsvSource(value = {"0:-1", "0:-2"}, delimiter = ':')
        void movable_givenInvalidDistanceAndEnemyTarget_thenReturnFalse(final int file, final int rank) {
            // given
            final Distance invalidDistance = new Distance(file, rank);

            // when
            final boolean actual = blackInitialPawn.movable(invalidDistance, enemy);

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("목적지가 빈 칸일 때 직진하는 거리가 2보다 크면 움직일 수 없다.")
        void movable_givenInvalidDistanceAndEmptyTarget_thenReturnFalse() {
            // given
            final Distance invalidDistance = new Distance(0, -3);

            // when
            final boolean actual = blackInitialPawn.movable(invalidDistance, empty);

            // then
            assertThat(actual).isFalse();
        }

        @ParameterizedTest(name = "움직이는 방향이 ({0},{1})과 같이 유효하지 않은 경우 목적지와 무관하게 움직일 수 없다.")
        @DisplayName("movable() 유효하지 않은 이동 방향 테스트")
        @CsvSource(value = {
                "1:1", "-1:1", "0:1", "1:0", "-1:0", "2:1", "2:-1", "-2:1", "-2:-1", "1:2", "1:-2", "-1:2", "-1:-2"
        }, delimiter = ':')
        void movable_giveInvalidDistance_thenReturnFalse(final int invalidFile, final int invalidRank) {
            // given
            final Distance invalidDistance = new Distance(invalidFile, invalidRank);

            // when
            final boolean actual = blackInitialPawn.movable(invalidDistance, empty);

            // then
            assertThat(actual).isFalse();
        }

        @ParameterizedTest(name = "목적지가 적군일 때 방향이 대각선이고 움직이는 거리가 1 보다 큰 ({0},{1})이라면 목적지와 무관하게 움직일 수 없다.")
        @DisplayName("movable() 유효한 공격 이동 방향, 유효하지 않은 공격 이동 거리")
        @CsvSource(value = {"2:-2", "-2:-2"}, delimiter = ':')
        void movable_givenAttackDirectionInvalidDistance_thenReturnFalse(final int invalidFile, final int invalidRank) {
            // given
            final Distance invalidDistance = new Distance(invalidFile, invalidRank);

            // when
            final boolean actual = blackInitialPawn.movable(invalidDistance, enemy);

            // then
            assertThat(actual).isFalse();
        }
    }

    @Nested
    @DisplayName("movable() 흰색 폰 테스트")
    class WhiteInitialPawnMovableMethodTest {

        private final Piece empty = Empty.EMPTY_PIECE;
        private Piece whiteInitialPawn;
        private Piece ally;
        private Piece enemy;

        @BeforeEach
        void beforeEach() {
            final Pawn whitePawn = new Pawn(Camp.WHITE);

            whiteInitialPawn = new InitialPawn(whitePawn);
            ally = new InitialPawn(whitePawn);

            final Pawn blackPawn = new Pawn(Camp.BLACK);

            enemy = new InitialPawn(blackPawn);
        }

        @ParameterizedTest(name = "목적지가 적군인 경우 움직이는 방향이 대각선 ({0},{1})일 때 움직일 수 있다.")
        @DisplayName("movable() 유효한 공격 이동 방향, 유효한 공격 이동 거리, 적군 테스트")
        @CsvSource(value = {"1:1", "-1:1"}, delimiter = ':')
        void movable_givenValidAttackDistanceAndEnemyTarget_thenReturnTrue(final int file, final int rank) {
            // given
            final Distance distance = new Distance(file, rank);

            // when
            final boolean actual = whiteInitialPawn.movable(distance, enemy);

            // then
            assertThat(actual).isTrue();
        }

        @ParameterizedTest(name = "목적지가 빈 곳이거나 아군인 경우 움직이는 방향이 대각선 ({0},{1}) 일 때 움직일 수 없다.")
        @DisplayName("movable() 유효한 공격 이동 방향, 유효한 공격 이동 거리, 아군 테스트")
        @CsvSource(value = {"1:1", "-1:1"}, delimiter = ':')
        void movable_givenValidAttackDistanceAndAllyTarget_thenReturnTrue(final int file, final int rank) {
            // given
            final Distance distance = new Distance(file, rank);

            // when
            final boolean actual = whiteInitialPawn.movable(distance, ally);

            // then
            assertThat(actual).isFalse();
        }

        @ParameterizedTest(name = "목적지가 적군이거나 아군일 경우 직진({0}, {1})으로 움직일 수 없다.")
        @DisplayName("movable() 실패 테스트")
        @CsvSource(value = {"0:1", "0:2"}, delimiter = ':')
        void movable_givenValidDistanceAndAllyTarget_thenReturnFalse(final int file, final int rank) {
            // given
            final Distance distance = new Distance(file, rank);

            // when
            final boolean actual = whiteInitialPawn.movable(distance, ally);

            // then
            assertThat(actual).isFalse();
        }

        @ParameterizedTest(name = "목적지가 빈 곳인 경우 직진 ({0}, {1})으로 움직일 수 있다.")
        @DisplayName("movable() 성공 테스트")
        @CsvSource(value = {"0:1", "0:2"}, delimiter = ':')
        void movable_givenValidDistanceAndEmptyTarget_thenReturnFalse() {
            // given
            final Distance distance = new Distance(0, 1);

            // when
            final boolean actual = whiteInitialPawn.movable(distance, empty);

            // then
            assertThat(actual).isTrue();
        }

        @ParameterizedTest(name = "움직이는 방향이 ({0},{1})과 같이 유효하지 않은 경우 목적지와 무관하게 움직일 수 없다.")
        @DisplayName("movable() 유효하지 않은 이동 방향 테스트")
        @CsvSource(value = {
                "1:-1", "-1:-1", "0:-1", "1:0", "-1:0", "2:1", "2:-1", "-2:1", "-2:-1", "1:2", "1:-2", "-1:2", "-1:-2"
        }, delimiter = ':')
        void movable_giveInvalidDistance_thenReturnFalse(final int invalidFile, final int invalidRank) {
            // given
            final Distance invalidDistance = new Distance(invalidFile, invalidRank);

            // when
            final boolean actual = whiteInitialPawn.movable(invalidDistance, empty);

            // then
            assertThat(actual).isFalse();
        }

        @ParameterizedTest(name = "목적지가 적군일 때 방향이 대각선이고 움직이는 거리가 1 보다 큰 ({0},{1})이라면 목적지와 무관하게 움직일 수 없다.")
        @DisplayName("movable() 유효한 공격 이동 방향, 유효하지 않은 공격 이동 거리 테스트")
        @CsvSource(value = {"2:2", "-2:2"}, delimiter = ':')
        void movable_givenInvalidDirection_thenReturnFalse(final int invalidFile, final int invalidRank) {
            // given
            final Distance invalidDistance = new Distance(invalidFile, invalidRank);

            // when
            final boolean actual = whiteInitialPawn.movable(invalidDistance, enemy);

            // then
            assertThat(actual).isFalse();
        }

        @ParameterizedTest(name = "({0}, {1})과 같이 유효하지 않은 거리일 경우 목적지와 상관 없이 움직일 수 없다.")
        @DisplayName("movable() 유효하지 않은 거리 테스트")
        @CsvSource(value = {"0:0", "3:0", "2:2"}, delimiter = ':')
        void movable_givenInvalidDistance_thenReturnFalse(final int invalidFile, final int invalidRank) {
            // given
            final Distance invalidDistance = new Distance(invalidFile, invalidRank);

            // when
            final boolean actual = whiteInitialPawn.movable(invalidDistance, enemy);

            // then
            assertThat(actual).isFalse();
        }
    }
}
