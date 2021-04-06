package domain.menu;

import domain.Board;
import domain.ChessGame;
import domain.exception.GameNotStartException;
import domain.state.Wait;
import dto.StatusDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EndTest {
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(new Wait(new Board()));
    }

    @DisplayName("end 메뉴를 실행하면, 현재 보드판을 출력하고 게임이 끝난다.")
    @Test
    void end_menu_test() {
        (new Start()).execute("start", chessGame);
        assertThat((new End()).execute("end", chessGame))
                .isEqualTo(new StatusDto(chessGame.blackScore(), chessGame.whiteScore()));
    }

    @DisplayName("게임을 실행하지 않고 end 메뉴를 실행하면, 에러가 발생한다.")
    @Test
    void end_menu_fail_test() {
        assertThatThrownBy(() -> (new End()).execute("end", chessGame))
                .isInstanceOf(GameNotStartException.class);
    }
}