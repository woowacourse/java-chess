package chess.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("게임을 시작하면 모든 기물들을 초기화 위치에 생성하고 DB에 저장한다.")
    void start() {
        //given
        final ChessGame chessGame = new ChessGame();
        chessGame.start();
        //actual
        final Map<String, Object> allPiecesByPosition = chessGame.getAllPiecesByPosition();
        //then
        assertThat(allPiecesByPosition).hasSize(32);
    }

    @Test
    @DisplayName("진행중인 게임이 있는데 게임을 시작하려고 하면 예외를 발생시킨다.")
    void startException() {
        //given
        final ChessGame chessGame = new ChessGame();
        chessGame.start();
        //when, then
        assertThatThrownBy(chessGame::start)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 진행중인 게임이 있습니다.");
    }
}
