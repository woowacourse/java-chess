package domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {

    @DisplayName("start를 입력해 Start 메뉴를 반환한다.")
    @Test
    void start_menu_select() {
        String command = "start";
        assertThat(Menu.findMenu(command)).isEqualTo(Menu.START);
    }

    @DisplayName("move를 입력해 Move 메뉴를 반환한다.")
    @Test
    void move_menu_select() {
        String command = "move";
        assertThat(Menu.findMenu(command)).isEqualTo(Menu.MOVE);
    }

    @DisplayName("end를 입력해 End 메뉴를 반환한다.")
    @Test
    void end_menu_select() {
        String command = "end";
        assertThat(Menu.findMenu(command)).isEqualTo(Menu.END);
    }

    @DisplayName("status를 입력해 Status 메뉴를 반환한다.")
    @Test
    void status_menu_select() {
        String command = "status";
        assertThat(Menu.findMenu(command)).isEqualTo(Menu.STATUS);
    }
}