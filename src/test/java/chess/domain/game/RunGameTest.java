package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunGameTest {
    
    @Test
    @DisplayName("실행 게임 종료 테스트")
    void end_game_test() {
        Game2 runGame = new ReadyGame().start();
        Game2 game = runGame.end();
        Assertions.assertThat(game).isInstanceOf(EndGame.class);
    }
    
    @Test
    @DisplayName("실행상태 게임 move 메서드 호출")
    void run_game_move_test() {
        Game2 runGame = new ReadyGame().start();
        Game2 game = runGame.move(Position.from("a2"), Position.from("a3"));
        Assertions.assertThat(game).isInstanceOf(RunGame.class);
    }
    
    @Test
    @DisplayName("실행상태 게임 status 메서드 호출")
    void run_game_status_test() {
        Game2 runGame = new ReadyGame().start();
        Status status = runGame.status();
        Assertions.assertThat(status.getWinner()).isEqualTo(Color.NONE);
    }
    
    
    @Test
    @DisplayName("실행상태 게임 메서드 호출 예외 테스트 - start")
    void run_game_exception_test() {
        Game2 runGame = new ReadyGame().start();
        Assertions.assertThatThrownBy(runGame::start)
                .isInstanceOf(IllegalStateException.class);
    }
    
    @Test
    @DisplayName("킹이 잡히면 게임이 종료된다.")
    void endWhenKingCaught() {
        Game2 chessGame = new ReadyGame().start();
        chessGame.move(Position.from("e2"), Position.from("e4"));
        chessGame.move(Position.from("e7"), Position.from("e5"));
        chessGame.move(Position.from("d1"), Position.from("h5"));
        chessGame.move(Position.from("f7"), Position.from("f5"));
        chessGame.move(Position.from("h5"), Position.from("e8"));
        Assertions.assertThat(chessGame.isOver()).isTrue();
        Assertions.assertThat(chessGame.isContinued()).isFalse();
    }
    
}