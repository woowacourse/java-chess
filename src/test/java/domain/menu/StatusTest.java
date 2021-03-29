package domain.menu;

import domain.ChessGame;
import domain.dto.StatusDto;
import domain.exception.GameNotStartException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StatusTest {
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame();
    }

    @DisplayName("status 메뉴를 실행하면, 현재 체스 게임의 결과를 출력한다.")
    @Test
    void status_menu_test() {
        (new Start()).execute("start", chessGame);
        assertThat((new Status()).execute("status", chessGame))
                .isEqualTo(new StatusDto(chessGame.piecesScore()));
    }

    @DisplayName("게임이 실행되지 않은 상태에서 status 메뉴를 실행하면, 에러가 발생한다.")
    @Test
    void start_menu_fail_test() {
        assertThatThrownBy(() -> (new Status()).execute("status", chessGame))
                .isInstanceOf(GameNotStartException.class);
    }
}