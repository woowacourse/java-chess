package chess.model.piece.type;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.piece.Camp;
import chess.model.piece.Piece;
import chess.model.position.Distance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    private final Piece blackPawn = new Pawn(Camp.BLACK);
    
    @Test
    @DisplayName("pick()은 호출하면 자기 자신을 반환한다")
    void pick_whenCall_thenReturnThis() {
        // when
        final Piece actual = blackPawn.pick();

        // then
        assertThat(actual).isSameAs(blackPawn);
    }

    @Test
    @DisplayName("isNotPassable()은 호출하면 true를 반환한다")
    void isNotPassable_whenCall_thenReturnFalse() {
        // when
        final boolean actual = blackPawn.isNotPassable();

        // then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest(name = "검은색 진영 폰은 isSameTeam()을 호출할 때 Camp.{0}을 건네주면 {1}을 반환한다")
    @DisplayName("isSameTeam() 테스트")
    @CsvSource(value = {"BLACK:true", "WHITE:false"}, delimiter = ':')
    void isSameTeam_givenCamp_thenReturnIsSameTeam(final Camp camp, final boolean expected) {
        // when
        final boolean actual = blackPawn.isSameTeam(camp);

        // then
        assertThat(actual).isSameAs(expected);
    }

    @Nested
    @DisplayName("movable() 검은색 폰 테스트")
    class BlackPawnMovableMethodTest {
        private final Piece blackPawn = new Pawn(Camp.BLACK);
        private final Piece ally = new Pawn(Camp.BLACK);
        private final Piece empty = Empty.EMPTY_PIECE;
        private final Piece enemy = new Pawn(Camp.WHITE);

        @ParameterizedTest(name = "목적지가 적군인 경우 움직이는 방향이 대각선 ({0}, {1})일 때 움직일 수 있다.")
        @DisplayName("movable() 유효한 공격 이동 방향, 유효한 공격 이동 거리, 적군 테스트")
        @CsvSource(value = {"1:-1", "-1:-1"}, delimiter = ':')
        void movable_givenValidAttackDistanceAndEnemyTarget_thenReturnTrue(final int file, final int rank) {
            // given
            final Distance distance = new Distance(file, rank);

            // when
            final boolean actual = blackPawn.movable(distance, enemy);

            // then
            assertThat(actual).isTrue();
        }

        @ParameterizedTest(name = "목적지가 아군인 경우 움직이는 방향이 대각선 ({0}, {1})일 때 움직일 수 없다.")
        @DisplayName("movable() 유효한 공격 이동 방향, 유효한 공격 이동 거리, 아군 테스트")
        @CsvSource(value = {"1:-1", "-1:-1"}, delimiter = ':')
        void movable_givenValidAttackDistanceAndAllyTarget_thenReturnTrue(final int file, final int rank) {
            // given
            final Distance distance = new Distance(file, rank);

            // when
            final boolean actual = blackPawn.movable(distance, ally);

            // then
            assertThat(actual).isFalse();
        }

        @ParameterizedTest(name = "목적지가 빈 곳인 경우 움직이는 방향이 대각선 ({0},{1})일 때 움직일 수 없다.")
        @DisplayName("movable() 유효한 공격 이동 방향, 유효한 공격 이동 거리, 빈 곳 테스트")
        @CsvSource(value = {"1:-1", "-1:-1"}, delimiter = ':')
        void movable_givenValidAttackDistanceAndEmptyTarget_thenReturnFalse(final int file, final int rank) {
            // given
            final Distance distance = new Distance(file, rank);

            // when
            final boolean actual = blackPawn.movable(distance, ally);

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("목적지가 적군인 경우 움직이는 방향이 직진 (0,-1)일 때 움직일 수 없다.")
        void movable_givenValidDistanceAndEnemyTarget_thenReturnFalse() {
            // given
            final Distance distance = new Distance(0, -1);

            // when
            final boolean actual = blackPawn.movable(distance, enemy);

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("목적지가 아군인 경우 움직이는 방향이 직진 (0,-1)일 때 움직일 수 없다.")
        void movable_givenValidDistanceAndAllyTarget_thenReturnFalse() {
            // given
            final Distance distance = new Distance(0, -1);

            // when
            final boolean actual = blackPawn.movable(distance, ally);

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("목적지가 빈 곳인 경우 움직이는 방향이 직진 (0,-1)일 때 움직일 수 있다.")
        void movable_givenValidDistanceAndEmptyTarget_thenReturnFalse() {
            // given
            final Distance distance = new Distance(0, -1);

            // when
            final boolean actual = blackPawn.movable(distance, empty);

            // then
            assertThat(actual).isTrue();
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
            final boolean actual = blackPawn.movable(invalidDistance, empty);

            // then
            assertThat(actual).isFalse();
        }

        @ParameterizedTest(name = "목적지가 적군일 때 방향이 대각선이고 움직이는 거리가 1 보다 큰 ({0},{1})이라면 목적지와 무관하게 움직일 수 없다.")
        @DisplayName("movable() 유효한 공격 이동 방향, 유효하지 않은 이동 거리, 적군 테스트")
        @CsvSource(value = {"2:-2", "-2:-2"}, delimiter = ':')
        void movable_givenAttackDirectionInvalidDistance_thenReturnFalse(final int invalidFile, final int invalidRank) {
            // given
            final Distance invalidDistance = new Distance(invalidFile, invalidRank);

            // when
            final boolean actual = blackPawn.movable(invalidDistance, enemy);

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("목적지가 빈 칸일 때 직진하는 거리가 1보다 큰 (0, -2)면 움직일 수 없다.")
        void movable_givenInvalidDistanceAndEmptyTarget_thenReturnFalse() {
            // given
            final Distance invalidDistance = new Distance(0, -2);

            // when
            final boolean actual = blackPawn.movable(invalidDistance, empty);

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("목적지가 아군일 때 직진하는 거리가 1보다 큰 (0, -2)면 움직일 수 없다.")
        void movable_givenInvalidDistanceAndAllyTarget_thenReturnFalse() {
            // given
            final Distance invalidDistance = new Distance(0, -2);

            // when
            final boolean actual = blackPawn.movable(invalidDistance, ally);

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("목적지가 적군일 때 직진하는 거리가 1보다 큰 (0, -2)면 움직일 수 없다.")
        void movable_givenInvalidDistanceAndEnemyTarget_thenReturnFalse() {
            // given
            final Distance invalidDistance = new Distance(0, -2);

            // when
            final boolean actual = blackPawn.movable(invalidDistance, enemy);

            // then
            assertThat(actual).isFalse();
        }
    }

    @Nested
    @DisplayName("movable() 흰색 폰 테스트")
    class WhitePawnMovableMethodTest {
        private final Piece whitePawn = new Pawn(Camp.WHITE);
        private final Piece ally = new Pawn(Camp.WHITE);
        private final Piece empty = Empty.EMPTY_PIECE;
        private final Piece enemy = new Pawn(Camp.BLACK);

        @ParameterizedTest(name = "목적지가 적군인 경우 움직이는 방향이 대각선 ({0},{1})일 때 움직일 수 있다.")
        @DisplayName("movable() 유효한 공격 이동 방향, 유효한 공격 이동 거리, 적군 테스트")
        @CsvSource(value = {"1:1", "-1:1"}, delimiter = ':')
        void movable_givenValidAttackDistanceAndEnemyTarget_thenReturnTrue(final int file, final int rank) {
            // given
            final Distance distance = new Distance(file, rank);

            // when
            final boolean actual = whitePawn.movable(distance, enemy);

            // then
            assertThat(actual).isTrue();
        }

        @ParameterizedTest(name = "목적지가 아군인 경우 움직이는 방향이 대각선 ({0},{1})일 때 움직일 수 없다.")
        @DisplayName("movable() 유효한 공격 이동 방향, 유효한 공격 이동 거리, 아군 테스트")
        @CsvSource(value = {"1:1", "-1:1"}, delimiter = ':')
        void movable_givenValidAttackDistanceAndAllyTarget_thenReturnTrue(final int file, final int rank) {
            // given
            final Distance distance = new Distance(file, rank);

            // when
            final boolean actual = whitePawn.movable(distance, ally);

            // then
            assertThat(actual).isFalse();
        }

        @ParameterizedTest(name = "목적지가 빈 곳인 경우 움직이는 방향이 대각선 ({0},{1})일 때 움직일 수 없다.")
        @DisplayName("movable() 유효한 공격 이동 방향, 유효한 공격 이동 거리, 빈 곳 테스트")
        @CsvSource(value = {"1:1", "-1:1"}, delimiter = ':')
        void movable_givenValidAttackDistanceAndEmptyTarget_thenReturnFalse(final int file, final int rank) {
            // given
            final Distance distance = new Distance(file, rank);

            // when
            final boolean actual = whitePawn.movable(distance, ally);

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("목적지가 적군인 경우 움직이는 방향이 직진 (0,1)일 때 움직일 수 없다.")
        void movable_givenValidDistanceAndEnemyTarget_thenReturnFalse() {
            // given
            final Distance distance = new Distance(0, 1);

            // when
            final boolean actual = whitePawn.movable(distance, enemy);

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("목적지가 아군인 경우 움직이는 방향이 직진 (0,1)일 때 움직일 수 없다.")
        void movable_givenValidDistanceAndAllyTarget_thenReturnFalse() {
            // given
            final Distance distance = new Distance(0, 1);

            // when
            final boolean actual = whitePawn.movable(distance, ally);

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("목적지가 빈 곳인 경우 움직이는 방향이 직진 (0,1)일 때 움직일 수 있다.")
        void movable_givenValidDistanceAndEmptyTarget_thenReturnFalse() {
            // given
            final Distance distance = new Distance(0, 1);

            // when
            final boolean actual = whitePawn.movable(distance, empty);

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
            final boolean actual = whitePawn.movable(invalidDistance, empty);

            // then
            assertThat(actual).isFalse();
        }

        @ParameterizedTest(name = "목적지가 적군일 때 방향이 대각선이고 움직이는 거리가 1 보다 큰 ({0},{1})이라면 목적지와 무관하게 움직일 수 없다.")
        @DisplayName("movable() 유효한 공격 이동 방향, 유효하지 않은 공격 이동 거리 테스트")
        @CsvSource(value = {"2:2", "-2:2"}, delimiter = ':')
        void tesT(final int invalidFile, final int invalidRank) {
            // given
            final Distance invalidDistance = new Distance(invalidFile, invalidRank);

            // when
            final boolean actual = whitePawn.movable(invalidDistance, enemy);

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("목적지가 빈 칸일 때 직진하는 거리가 1보다 큰 (0, 2)면 움직일 수 없다.")
        void movable_givenInvalidDistanceAndEmptyTarget_thenReturnFalse() {
            // given
            final Distance invalidDistance = new Distance(0, 2);

            // when
            final boolean actual = whitePawn.movable(invalidDistance, empty);

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("목적지가 아군일 때 직진하는 거리가 1보다 큰 (0, 2)면 움직일 수 없다.")
        void movable_givenInvalidDistanceAndAllyTarget_thenReturnFalse() {
            // given
            final Distance invalidDistance = new Distance(0, 2);

            // when
            final boolean actual = whitePawn.movable(invalidDistance, ally);

            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("목적지가 적군일 때 직진하는 거리가 1보다 큰 (0, 2)면 움직일 수 없다.")
        void movable_givenInvalidDistanceAndEnemyTarget_thenReturnFalse() {
            // given
            final Distance invalidDistance = new Distance(0, 2);

            // when
            final boolean actual = whitePawn.movable(invalidDistance, enemy);

            // then
            assertThat(actual).isFalse();
        }
    }
}
