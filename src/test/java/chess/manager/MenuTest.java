package chess.manager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {

    @Test
    @DisplayName("문자열을 입력받아 매뉴 커맨드와 명령어 개수가 일치하면 반환해준다.")
    void of() {
        String start = "start";
        String end = "end";
        String move = "move a1 a2";
        String status = "status";
        String show = "show a2";

        Menu startMenu = Menu.of(start);
        Menu endMenu = Menu.of(end);
        Menu moveMenu = Menu.of(move);
        Menu statusMenu = Menu.of(status);
        Menu showMenu = Menu.of(show);

        assertThat(startMenu).isEqualTo(Menu.START);
        assertThat(endMenu).isEqualTo(Menu.END);
        assertThat(moveMenu).isEqualTo(Menu.MOVE);
        assertThat(statusMenu).isEqualTo(Menu.STATUS);
        assertThat(showMenu).isEqualTo(Menu.SHOW);
    }

    @Test
    @DisplayName("메뉴가 END인지 판단한다.")
    void isEnd() {
        Menu end = Menu.END;
        Menu start = Menu.START;

        assertThat(end.isEnd()).isTrue();
        assertThat(start.isEnd()).isFalse();
    }

    @Test
    void isMove() {
        Menu move = Menu.MOVE;
        Menu start = Menu.START;

        assertThat(move.isMove()).isTrue();
        assertThat(start.isMove()).isFalse();
    }

    @Test
    void isStatus() {
        Menu status = Menu.STATUS;
        Menu start = Menu.START;

        assertThat(status.isStatus()).isTrue();
        assertThat(start.isStatus()).isFalse();
    }

    @Test
    void isStart() {
        Menu start = Menu.START;
        Menu status = Menu.STATUS;

        assertThat(start.isStart()).isTrue();
        assertThat(status.isStart()).isFalse();
    }

    @Test
    void isShow() {
        Menu show = Menu.SHOW;
        Menu start = Menu.START;

        assertThat(show.isShow()).isTrue();
        assertThat(start.isShow()).isFalse();
    }
}