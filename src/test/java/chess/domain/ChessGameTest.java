package chess.domain;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    @DisplayName("초기 게임 상황은 두 킹 모두 살아있다.")
    void throw_exception_when_both_king_alive() {
        assertThatThrownBy(() -> chessGame.getWinner())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("아직 게임이 끝나지 않았습니다.");
    }

    @Test
    @DisplayName("백 킹이 죽었으면 흑의 승리다.")
    void black_win_when_white_king_dead() {
        chessGame.move(Square.of(File.D, Rank.TWO), Square.of(File.D, Rank.FOUR));
        chessGame.move(Square.of(File.E, Rank.SEVEN), Square.of(File.E, Rank.FIVE));
        chessGame.move(Square.of(File.A, Rank.TWO), Square.of(File.A, Rank.FOUR));
        chessGame.move(Square.of(File.F, Rank.EIGHT), Square.of(File.B, Rank.FOUR));
        chessGame.move(Square.of(File.H, Rank.TWO), Square.of(File.H, Rank.THREE));
        chessGame.move(Square.of(File.B, Rank.FOUR), Square.of(File.E, Rank.ONE));
        assertThat(chessGame.getWinner()).isEqualTo(BLACK);
    }

    @Test
    @DisplayName("흑 킹이 죽었으면 백의 승리다.")
    void white_win_when_black_king_dead() {
        chessGame.move(Square.of(File.E, Rank.TWO), Square.of(File.E, Rank.FOUR));
        chessGame.move(Square.of(File.D, Rank.SEVEN), Square.of(File.D, Rank.SIX));
        chessGame.move(Square.of(File.F, Rank.ONE), Square.of(File.B, Rank.FIVE));
        chessGame.move(Square.of(File.H, Rank.SEVEN), Square.of(File.H, Rank.SIX));
        chessGame.move(Square.of(File.B, Rank.FIVE), Square.of(File.E, Rank.EIGHT));
        assertThat(chessGame.getWinner()).isEqualTo(WHITE);
    }

    @Test
    @DisplayName("두 킹 모두 죽어있으면 예외가 발생한다.")
    void throw_exception_when_both_king_dead() {
        chessGame.move(Square.of(File.D, Rank.TWO), Square.of(File.D, Rank.FOUR));
        chessGame.move(Square.of(File.E, Rank.SEVEN), Square.of(File.E, Rank.FIVE));
        chessGame.move(Square.of(File.E, Rank.TWO), Square.of(File.E, Rank.FOUR));
        chessGame.move(Square.of(File.D, Rank.SEVEN), Square.of(File.D, Rank.FIVE));
        chessGame.move(Square.of(File.F, Rank.ONE), Square.of(File.B, Rank.FIVE));
        chessGame.move(Square.of(File.F, Rank.EIGHT), Square.of(File.B, Rank.FOUR));
        chessGame.move(Square.of(File.B, Rank.FIVE), Square.of(File.E, Rank.EIGHT));
        chessGame.move(Square.of(File.B, Rank.FOUR), Square.of(File.E, Rank.ONE));
        assertThatThrownBy(() -> chessGame.getWinner())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("두 킹이 모두 죽어있을 순 없습니다.");
    }

    @Test
    @DisplayName("두 킹 모두 살아있으면 false를 반환한다.")
    void is_king_dead_false() {
        assertThat(chessGame.isKingDead()).isFalse();
    }

    @Test
    @DisplayName("백 킹이 죽었으면 true 반환한다.")
    void is_king_dead_true_when_white_king_dead() {
        chessGame.move(Square.of(File.D, Rank.TWO), Square.of(File.D, Rank.FOUR));
        chessGame.move(Square.of(File.E, Rank.SEVEN), Square.of(File.E, Rank.FIVE));
        chessGame.move(Square.of(File.A, Rank.TWO), Square.of(File.A, Rank.FOUR));
        chessGame.move(Square.of(File.F, Rank.EIGHT), Square.of(File.B, Rank.FOUR));
        chessGame.move(Square.of(File.H, Rank.TWO), Square.of(File.H, Rank.THREE));
        chessGame.move(Square.of(File.B, Rank.FOUR), Square.of(File.E, Rank.ONE));
        assertThat(chessGame.isKingDead()).isTrue();
    }

    @Test
    @DisplayName("흑 킹이 죽었으면 true 반환한다.")
    void is_king_dead_true_when_black_king_dead() {
        chessGame.move(Square.of(File.E, Rank.TWO), Square.of(File.E, Rank.FOUR));
        chessGame.move(Square.of(File.D, Rank.SEVEN), Square.of(File.D, Rank.SIX));
        chessGame.move(Square.of(File.F, Rank.ONE), Square.of(File.B, Rank.FIVE));
        chessGame.move(Square.of(File.H, Rank.SEVEN), Square.of(File.H, Rank.SIX));
        chessGame.move(Square.of(File.B, Rank.FIVE), Square.of(File.E, Rank.EIGHT));
        assertThat(chessGame.isKingDead()).isTrue();
    }
}
