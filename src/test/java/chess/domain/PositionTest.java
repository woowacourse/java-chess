package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PositionTest {
    @ParameterizedTest
    @CsvSource(value = {"1a,0,0", "1b,0,1", "2a,1,0", "1h,0,7", "2h,1,7", "8a,7,0", "8b,7,1", "8h,7,7"})
    @DisplayName("사용자가 체스 판에 있는 문자를 입력하면, 알맞은 Position 객체를 반환한다")
    void position_with_user_input(final String input, final int x, final int y) {
        Position userInputPosition = Position.of(input);
        Position boardPosition = new Position(x, y);
        assertThat(userInputPosition).isEqualTo(boardPosition);
    }
}
