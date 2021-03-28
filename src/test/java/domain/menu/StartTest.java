package domain.menu;

import domain.ChessGame;
import domain.dto.BoardDto;
import domain.exception.AlreadyStartException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StartTest {
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame();
    }

    @DisplayName("start 메뉴를 실행하면, 첫 시작된 보드판을 출력한다.")
    @Test
    void start_menu_test() {
        assertThat((new Start()).execute("start", chessGame))
                .isEqualTo(new BoardDto(chessGame.getBoard()));
    }

    @DisplayName("게임이 실행된 상태에서 start 메뉴를 실행하면, 에러가 발생한다.")
    @Test
    void start_menu_fail_test() {
        (new Start()).execute("start", chessGame);
        assertThatThrownBy(() -> (new Start()).execute("start", chessGame))
                .isInstanceOf(AlreadyStartException.class);
    }
}