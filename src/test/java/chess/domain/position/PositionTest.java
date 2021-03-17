package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {
    @DisplayName("Position의 equals 테스트")
    @ParameterizedTest
    @EnumSource(File.class)
    void getPositionTest(File file) {
        for (Rank rank : Rank.values()) {
            Position position = Position.of(file, rank);

            assertThat(position).isEqualTo(Position.of(file, rank));
        }
    }

    @DisplayName("위치 형식에 2글자가 아닐  예외를 발생시킨다.")
    @Test
    void throwExceptionWhenWrongFormatArgument() {
        assertThatThrownBy(() -> Position.of("aaa"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("위치 형식에 맞는 입력이 아닙니다.");
    }

}