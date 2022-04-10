package chess.database.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.database.GameStateGenerator;
import chess.domain.board.Board;
import chess.domain.board.BoardFixtures;
import chess.domain.game.GameState;
import chess.domain.game.Ready;

class GameStateGeneratorTest {

    @Test
    @DisplayName("상태와 색깔 문자열로 상태 객체를 만든다.")
    public void createStateByStateAndColorString() {
        // given
        Board board = BoardFixtures.EMPTY;
        List<String> stateAndColor = List.of("READY", "WHITE");
        // when
        GameState generated = GameStateGenerator.generate(board, stateAndColor);
        // then
        assertThat(generated).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("리스트의 길이가 맞지 않으면 예외를 던진다.")
    public void throwsExceptionWithInvalidSize() {
        // given & when
        Board board = BoardFixtures.EMPTY;
        List<String> stateAndColor = List.of("READY", "WHITE", "wrong");
        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> GameStateGenerator.generate(board, stateAndColor));
    }
}