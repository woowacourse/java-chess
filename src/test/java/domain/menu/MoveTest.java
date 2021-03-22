package domain.menu;

import domain.ChessGame;
import domain.dto.BoardDto;
import domain.exception.GameNotStartException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoveTest {
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame();
    }

    @DisplayName("move 메뉴를 실행하면, 이동된 보드판을 출력하고 게임이 끝난다.")
    @Test
    void move_menu_test() {
        (new Start()).execute("start", chessGame);
        assertThat((new Move()).execute("move e2 e4", chessGame))
                .isEqualTo(new BoardDto(chessGame.getBoard()));
    }


    @DisplayName("게임을 실행하지 않고 move 메뉴를 실행하면, 에러가 발생한다.")
    @Test
    void move_menu_fail_test() {
        assertThatThrownBy(() -> (new Move()).execute("move", chessGame))
                .isInstanceOf(GameNotStartException.class);
    }
}