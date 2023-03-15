package chess.model.piece;

import static chess.model.piece.PieceFixture.WHITE_KING;
import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Distance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @Nested
    @DisplayName("movable() 테스트를 진행한다.")
    class MovableTest {

        @ParameterizedTest(name = "KING이 ({0}, {1})로 이동할 때 movable 결과가 {2}로 나온다.")
        @CsvSource({"0,1,true", "1,0,true", "-1,-1,true", "1,-1,true", "1,1,true", "-1,1,true"})
        void isRightDirection_givenRankAndFile_thenReturnIfMovable(
                final int rank,
                final int file,
                final boolean result
        ) {
            // given
            final Distance distance = new Distance(rank, file);

            // when
            final boolean movable = WHITE_KING.movable(distance);

            // then
            assertThat(movable).isEqualTo(result);
        }

        @ParameterizedTest(name = "KING이 ({0}, {1})로 이동할 때 movable 결과가 {2}로 나온다.")
        @CsvSource({"0,1,true", "1,0,true", "-1,-1,true", "1,-1,true", "1,1,true", "-1,1,true",
                "0,2,false", "2,0,false", "-2,-2,false", "1,-2,false", "2,2,false", "-2,1,false",
        })
        void isUnAvailableDistance_givenDistance_thenReturnIfMovable(
                final int rank,
                final int file,
                final boolean result
        ) {
            // given
            final Distance distance = new Distance(rank, file);

            // when
            final boolean movable = WHITE_KING.movable(distance);

            // then
            assertThat(movable).isEqualTo(result);
        }
    }
}
