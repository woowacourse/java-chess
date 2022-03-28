package chess.domain.state;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.board.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayingTest {

    @DisplayName("게임 진행중일 때 게임이 끝났는지 확인하면 거짓을 응답한다.")
    @Test
    void isEnd() {
        GameState playing = new WhiteTurn(new Board(BoardInitializer.initBoard()));
        assertThat(playing.isEnd()).isFalse();
    }

    @DisplayName("게임 진행중일 때 승자를 찾으려하면 예외가 발생한다.")
    @Test
    void findWinner() {
        GameState playing = new WhiteTurn(new Board(BoardInitializer.initBoard()));
        assertThatThrownBy(playing::findWinner)
                .isInstanceOf(IllegalStateException.class).hasMessage("게임이 아직 진행 중 입니다.");
    }
}