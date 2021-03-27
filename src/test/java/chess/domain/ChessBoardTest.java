package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {

    @DisplayName("ChessBoard 객체 생성 확인")
    @Test
    void 현재_기물_객체_생성() {
        ChessBoard chessBoard = ChessBoard.generate();
        assertThat(chessBoard.getChessBoard().size()).isEqualTo(32);
    }
}
