package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
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
        assertThatThrownBy(() -> chessGame.move(Square.of(File.A, Rank.SEVEN), Square.of(File.A, Rank.FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대팀 말을 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("흰색 턴에는 흰색 기물을 움직일 수 있다.")
    void move_white_success_when_white_turn() {
        assertThatCode(() -> chessGame.move(Square.of(File.A, Rank.TWO), Square.of(File.A, Rank.FOUR)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("검은 색 턴에는 검은 색 기물을 움직일 수 있다.")
    void move_black_success_when_black_turn() {
        chessGame.move(Square.of(File.A, Rank.TWO), Square.of(File.A, Rank.FOUR));
        assertThatCode(() -> chessGame.move(Square.of(File.A, Rank.SEVEN), Square.of(File.A, Rank.FIVE)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("검은 색 턴에는 흰 색 기물을 움직일 수 없다.")
    void move_white_fail_when_black_turn() {
        chessGame.move(Square.of(File.A, Rank.TWO), Square.of(File.A, Rank.FOUR));
        assertThatThrownBy(() -> chessGame.move(Square.of(File.A, Rank.FOUR), Square.of(File.A, Rank.FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대팀 말을 움직일 수 없습니다.");
    }
}
