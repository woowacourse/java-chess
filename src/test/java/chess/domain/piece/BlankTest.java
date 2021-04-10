package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlankTest {
    @Test
    @DisplayName("캐싱된 빈 객체 생성")
    void create() {
        assertThat(Blank.INSTANCE).isSameAs(Blank.INSTANCE);
    }

    @Test
    @DisplayName("빈공간 객체 이름 가져오")
    void name() {
        assertThat(Blank.INSTANCE.getPieceName()).isEqualTo(".");
    }

}
