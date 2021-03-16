package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class KingTest {
    @Test
    @DisplayName("King 인스턴스를 정상적으로 생성하는 지 테스트")
    public void init() {
        assertThatCode(() -> {
            new King(false, 'a', '1');
        }).doesNotThrowAnyException();
    }
}
