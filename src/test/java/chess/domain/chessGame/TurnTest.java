package chess.domain.chessGame;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {

    @Test
    @DisplayName("게임을 시작한 경우 턴의 활성화를 확인할 수 있다")
    void should_active_when_playing_game() {
        Turn turn = Turn.create();
        turn.startGame();
        assertThat(turn.isActive()).isTrue();
    }

    @Test
    @DisplayName("게임 중이 아닌 경우 턴의 비활성화를 확인할 수 있다")
    void should_inactive_when_not_playing_game() {
        Turn turn = Turn.create();
        turn.stopGame();
        assertThat(turn.isActive()).isFalse();
    }

    @Test
    @DisplayName("피스와 같은 색일 경우 유효한 턴임을 확인할 수 있다")
    void should_valid_turn_when_same_color_piece() {
        Piece piece = new King(Color.WHITE);
        Turn turn = Turn.create();
        turn.startGame();

        assertThat(turn.isValidTurn(piece)).isTrue();
    }

    @Test
    @DisplayName("피스와 다른 색일 경우 유효하지 않은 턴임을 확인할 수 있다")
    void should_invalid_turn_when_not_same_color_piece() {
        Piece piece = new King(Color.BLACK);
        Turn turn = Turn.create();
        turn.startGame();

        assertThat(turn.isValidTurn(piece)).isFalse();
    }
}
