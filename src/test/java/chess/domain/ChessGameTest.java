package chess.domain;

import chess.domain.pieces.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ChessGameTest {

    ChessGame chessGame;

    @BeforeEach
    void setUp(){
        chessGame=new ChessGame();
    }

    @Test
    void 정상_생성_Test() {
        assertDoesNotThrow(ChessGameTest::new);
    }

    @Test
    void 턴의_색깔_변경_Test() {
        assertThat(chessGame.changeColorOfTurn()).isEqualTo(Color.BLACK);
    }


}
