package chess.view;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveInfoTest {

    @DisplayName("무브 이동에 관한 데이터를 담은 MoveInfo 를 생성한다")
    @Test
    void create() {
        // given
        String[] info = new String[]{"move", "a1", "a2"};
        // when
        assertThatNoException().isThrownBy(() -> new MoveInfo(info));
        // then
    }

    @DisplayName("MoveInfo 데이터를 검증한다.")
    @Test
    void create_false() {
        // given
        String[] info = new String[]{"move", "a1"};
        // when
        assertThatThrownBy(() -> new MoveInfo(info)).hasMessage("이동하고자 하는 위치와 도착 위치를 함께 입력해주세요.");
        // then
    }
}