package chess.domain.piece.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PawnDirectionTest {

    @Nested
    @DisplayName("폰이 이동 가능한 위치들을 올바르게 검열하는지 확인하는 테스트")
    class CanMove {

        @ParameterizedTest
        @CsvSource(value = {"0,1,true", "1,1,true", "-1,1,true", "0,2,false", "1,2,false", "1,-1,false"})
        @DisplayName("백팀인 경우")
        void is_exist_when_white(int fileDifference, int rankDifference, boolean expected) {
            PawnDirection pawnDirection = PawnDirection.UPPER;

            assertThat(pawnDirection.isExist(fileDifference, rankDifference)).isEqualTo(expected);
        }

        @ParameterizedTest
        @CsvSource(value = {"0,-1,true", "1,-1,true", "-1,-1,true", "0,-2,false", "1,-2,false", "1,1,false"})
        @DisplayName("흑팀인 경우")
        void is_exist_when_black(int fileDifference, int rankDifference, boolean expected) {
            PawnDirection pawnDirection = PawnDirection.LOWER;

            assertThat(pawnDirection.isExist(fileDifference, rankDifference)).isEqualTo(expected);
        }
    }
}