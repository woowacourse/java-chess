package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmptyTest {

    @DisplayName("Empty 객체 생성 확인")
    @Test
    void 빈_기물_객체_생성_테스트() {
        Empty empty = new Empty(Position.of('0', '0'), ".");

        assertThat(empty.getPosition()).isEqualTo(Position.of('0', '0'));
        assertThat(empty.getName()).isEqualTo(".");
    }
}
