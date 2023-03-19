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

class QueenTest {

    private final Piece blackQueen = new Queen(Camp.BLACK);
    
    @Test
    @DisplayName("pick()은 호출하면 자기 자신을 반환한다")
    void pick_whenCall_thenReturnThis() {
        // when
        final Piece actual = blackQueen.pick();

        // then
        assertThat(actual).isSameAs(blackQueen);
    }

    @Test
    @DisplayName("isNotPassable()은 호출하면 true를 반환한다")
    void isNotPassable_whenCall_thenReturnFalse() {
        // when
        final boolean actual = blackQueen.isNotPassable();

        // then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest(name = "검은색 진영 비숍은 isSameTeam()을 호출할 때 Camp.{0}을 건네주면 {1}을 반환한다")
    @CsvSource(value = {"BLACK:true", "WHITE:false"}, delimiter = ':')
    void isSameTeam_givenCamp_thenReturnIsSameTeam(final Camp camp, final boolean expected) {
        // when
        final boolean actual = blackQueen.isSameTeam(camp);

        // then
        assertThat(actual).isSameAs(expected);
    }

    @Nested
    @DisplayName("movable() 테스트")
    class QueenMovableMethodTest {
        private final Piece ally = new Queen(Camp.BLACK);
        private final Piece empty = Empty.EMPTY;
        private final Piece enemy = new Queen(Camp.WHITE);

        @ParameterizedTest(name = "목적지가 적군인 경우 움직이는 방향이 ({0} / {1})일 때 움직일 수 있다.")
        @CsvSource(value = {"1:1", "1:-1", "-1:-1", "-1:1", "0:1", "0:-1", "1:0", "-1:0"}, delimiter = ':')
        void movable_givenValidDistanceAndEnemyTarget_thenReturnTrue(final int file, final int rank) {
            // given
            final Distance distance = new Distance(file, rank);

            // when
            final boolean actual = blackQueen.movable(distance, enemy);

            // then
            assertThat(actual).isTrue();
        }

        @ParameterizedTest(name = "목적지가 빈 곳인 경우 움직이는 방향이 ({0} / {1})일 때 움직일 수 있다.")
        @CsvSource(value = {"1:1", "1:-1", "-1:-1", "-1:1", "0:1", "0:-1", "1:0", "-1:0"}, delimiter = ':')
        void movable_givenValidDistanceAndEmptyTarget_thenReturnTrue(final int file, final int rank) {
            // given
            final Distance distance = new Distance(file, rank);

            // when
            final boolean actual = blackQueen.movable(distance, enemy);

            // then
            assertThat(actual).isTrue();
        }

        @Test
        @DisplayName("목적지가 아군인 경우 거리와 무관하게 움직일 수 없다.")
        void movable_givenValidDistanceAndAllyTarget_thenReturnFalse() {
            // given
            final Distance distance = new Distance(1, 1);

            // when
            final boolean actual = blackQueen.movable(distance, ally);

            // then
            assertThat(actual).isFalse();
        }

        @ParameterizedTest(name = "움직이는 방향 ({0} / {1})과 같이 유효하지 않은 경우 목적지와 무관하게 움직일 수 없다.")
        @CsvSource(value = {
                "2:1", "2:-1", "-2:1", "-2:-1", "1:2", "1:-2", "-1:2", "-1:-2"
        }, delimiter = ':')
        void movable_giveInvalidDistance_thenReturnFalse(final int invalidFile, final int invalidRank) {
            // given
            final Distance invalidDistance = new Distance(invalidFile, invalidRank);

            // when
            final boolean actual = blackQueen.movable(invalidDistance, empty);

            // then
            assertThat(actual).isFalse();
        }
    }
}
