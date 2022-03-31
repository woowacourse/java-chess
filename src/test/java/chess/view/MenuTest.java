package chess.view;

import static org.assertj.core.api.Assertions.assertThat;

import chess.controller.menu.End;
import chess.controller.menu.MenuType;
import chess.controller.menu.Move;
import chess.controller.menu.Start;
import chess.controller.menu.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @DisplayName("Start 생성확인")
    @Test
    void start() {
        // given
        String[] values = {"start"};
        // then
        assertThat(MenuType.of(values) instanceof Start).isTrue();
    }

    @DisplayName("Move 생성확인")
    @Test
    void move() {
        // given
        String[] values = {"move", "a1", "a2"};
        // then
        assertThat(MenuType.of(values) instanceof Move).isTrue();
    }

    @DisplayName("Status 생성확인")
    @Test
    void status() {
        // given
        String[] values = {"status"};
        // then
        assertThat(MenuType.of(values) instanceof Status).isTrue();
    }

    @DisplayName("End 생성확인")
    @Test
    void end() {
        // given
        String[] values = {"end"};
        // then
        assertThat(MenuType.of(values) instanceof End).isTrue();
    }
}