package chess.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CommandTest {

    @DisplayName("정상 커맨드 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"start", "end", "move a2 b3"})
    public void start(String input) {
        //given & when & then
        Assertions.assertDoesNotThrow(() -> Command.from(input));
    }

    @DisplayName("체스판을 넘어가는 경우 테스트")
    @Test
    public void end() {
        //given
        String input = "move a10 b2";

        //given & when & then
        Assertions.assertDoesNotThrow(() -> Command.from(input));
    }
}
