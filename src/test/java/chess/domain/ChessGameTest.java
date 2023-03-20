package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.dto.SquareDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame();
    }

    @Test
    @DisplayName("흰색 턴에는 검은 색 기물을 움직일 수 없다.")
    void move_black_fail_when_white_turn() {
        assertThatThrownBy(() -> chessGame.move(SquareDto.of("a7"), SquareDto.of("a5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대팀 말을 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("흰색 턴에는 흰색 기물을 움직일 수 있다.")
    void move_white_success_when_white_turn() {
        assertThatCode(() -> chessGame.move(SquareDto.of("a2"), SquareDto.of("a4")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("검은 색 턴에는 검은 색 기물을 움직일 수 있다.")
    void move_black_success_when_black_turn() {
        chessGame.move(SquareDto.of("a2"), SquareDto.of("a4"));
        assertThatCode(() -> chessGame.move(SquareDto.of("a7"), SquareDto.of("a5")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("검은 색 턴에는 흰 색 기물을 움직일 수 없다.")
    void move_white_fail_when_black_turn() {
        chessGame.move(SquareDto.of("a2"), SquareDto.of("a4"));
        assertThatThrownBy(() -> chessGame.move(SquareDto.of("a4"), SquareDto.of("a5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대팀 말을 움직일 수 없습니다.");
    }
}
