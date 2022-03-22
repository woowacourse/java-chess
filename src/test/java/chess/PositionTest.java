package chess;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("위치 값의 동일성을 테스트한다.")
    void position_equals() {
        //given
        Position position = new Position(Rank.ONE, File.A);

        //then
        assertThat(position).isEqualTo(new Position(Rank.ONE, File.A));
    }
}
