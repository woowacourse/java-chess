package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("end 명령어가 주어졌을 때 게임은 실행중이 아니다")
    void fromCommand() {
        final ChessGame chessGame = new ChessGame();
        chessGame.execute("end");
        Assertions.assertThat(chessGame.isRunning()).isFalse();
    }

    @Test
    @DisplayName("게임이 잘 실행되는지 확인한다.")
    void isRunning() {
        final ChessGame chessGame = new ChessGame();

        Assertions.assertThatCode(() -> {
                    chessGame.execute("start");
                    chessGame.execute("move e2 e4");
                    chessGame.execute("move c7 c6");
                    chessGame.execute("move d2 d4");
                    chessGame.execute("move d7 d5");
                    chessGame.execute("move b1 c3");
                    chessGame.execute("move d5 e4");
                    chessGame.execute("move c3 e4");
                    chessGame.execute("move b8 d7");
                    chessGame.execute("move d1 e2");
                    chessGame.execute("move g8 f6");
                    chessGame.execute("move e4 d6");
                    chessGame.execute("end");
                }
        ).doesNotThrowAnyException();

    }

}
