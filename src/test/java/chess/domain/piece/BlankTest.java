package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlankTest {

    @DisplayName("빈 칸 기물이 생성되었을 때 점으로 표시된다.")
    @Test
    void constructor() {
        Blank blank = new Blank(new Position("a1"));

        assertThat(blank.getSignature()).isEqualTo(".");
    }

    @DisplayName("빈 칸에 이동 명령 호출 시 예외가 발생한다.")
    @Test
    void move_Blank_Fails() {
        Blank blank = new Blank(new Position("a3"));

        assertThatThrownBy(() -> blank.move(new Blank(new Position("a4"))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("빈 칸은 이동할 수 없습니다.");
    }
}
