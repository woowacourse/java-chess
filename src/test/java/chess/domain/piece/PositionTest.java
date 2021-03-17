package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class PositionTest {
    @Test
    @DisplayName("포지션 객체가 정상적으로 생성됐는 지 예외를 안 던지는 지 테스트")
    public void init() {
        assertThatCode(() -> {
            new Position('a', '1');
        }).doesNotThrowAnyException();
    }
}
