package chess.domain;

import chess.domain.piece.CurrentPieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessGameTest {

    @DisplayName("체스 게임 내 체스판 확인하기")
    @Test
    void 체스판_확인_테스트() {
        ChessGame chessGame = new ChessGame();

        assertThat(chessGame.getChessBoard()).isInstanceOf(CurrentPieces.class);
    }
}
