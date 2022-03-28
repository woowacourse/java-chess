package chess.state;

import chess.ChessBoard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @Test
    @DisplayName("start 메서드 실행 후 Running으로 변경")
    void callStartAfterRunning() {
        State ready = new Ready(ChessBoard.createChessBoard());

        Assertions.assertThat(ready.start()).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("finished 메서드 실행 후 Finished로 변경")
    void callFinishedAfterFinished() {
        State ready = new Ready(ChessBoard.createChessBoard());

        Assertions.assertThat(ready.finished()).isInstanceOf(Finished.class);
    }
}
