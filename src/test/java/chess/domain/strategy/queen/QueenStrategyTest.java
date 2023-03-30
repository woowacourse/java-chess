package chess.domain.strategy.queen;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.Color;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

final class QueenStrategyTest {

    @DisplayName("올바른 위치일 경우 타겟 포지션이 반환된다.")
    @ParameterizedTest(name = "rank: {0}, file: {1}")
    @CsvSource({"3,e", "4,e", "1,b", "3,h", "0,g", "3,g"})
    void returnTargetPosition(int rank, char file) {
        //given
        //when
        //then
        assertDoesNotThrow(() -> new QueenStrategy().validateDirection(
                Position.from(3, 'd'),
                Position.from(rank, file),
                Color.WHITE,
                false
        ));
    }

    @Nested
    @DisplayName("올바르지 않은 위치일 경우 예외가 발생한다")
    class Invalid {
        @ParameterizedTest(name = "rank: {0}, file: {1}")
        @CsvSource({"5, e", "6, c", "0, b", "1, h"})
        void throwException(int rank, char file) {
            //given
            //when
            //then
            assertThatThrownBy(() -> new QueenStrategy().validateDirection(
                    Position.from(3, 'd'),
                    Position.from(rank, file),
                    Color.WHITE,
                    false
            ));
        }
    }

}
