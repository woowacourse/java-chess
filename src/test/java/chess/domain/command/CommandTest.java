package chess.domain.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {
    @Test
    @DisplayName("move 커맨드 정상 생성 확인")
    void move() {
        Command command = new Command("move a2 a3");
        assertThat(command.getOptions()).containsExactly("a2", "a3");
    }

    @Test
    @DisplayName("move 커맨드 옵션 값 별개 입력 에러")
    void moveError() {
        assertThatThrownBy(() -> new Command("move")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Command("move a2 a3 a5")).isInstanceOf(IllegalArgumentException.class);
    }
}