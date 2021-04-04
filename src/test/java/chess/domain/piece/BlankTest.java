package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlankTest {

    @Test
    @DisplayName("빈공간 객체 생성")
    void create() {
        assertThat(Blank.INSTANCE.getPieceName()).isEqualTo(".");
    }

    @Test
    @DisplayName("공백은 움직일 수 없다.")
    void fail_move_position() {
        //todo: 수정
    }

}
