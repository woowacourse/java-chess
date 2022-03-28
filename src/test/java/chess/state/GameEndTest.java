package chess.state;

import static org.assertj.core.api.Assertions.*;

import chess.ChessBoard;
import chess.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameEndTest {

    @Test
    @DisplayName("finished 호출 후 finished 상태로 변한다.")
    void callFinishedAfterFinishedState() {
        State gameEnd = new GameEnd(ChessBoard.createChessBoard(), Color.WHITE);

        assertThat(gameEnd.finished()).isInstanceOf(Finished.class);
    }
}
