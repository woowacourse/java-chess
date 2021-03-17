package chess.domain;

import chess.domain.piece.CurrentPieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessGameTest {

    @DisplayName("현재 기물 확인하기")
    @Test
    void 현재_기물_확인_테스트() {
        ChessGame chessGame = new ChessGame();

        assertThat(chessGame.getCurrentPieces()).isInstanceOf(CurrentPieces.class);
    }

//    @DisplayName("source 위치 ")
//    @Test
//    void name() {
//    }
}
