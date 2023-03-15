package domain.chessGame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import domain.piece.Piece;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

@DisplayName("ChessBoard는 ")
class ChessBoardTest {

    @Test
    @DisplayName("초기화 시 32개의 체스말이 세팅된다.")
    void getChessBoardTest() {
        // given
        ChessBoard chessBoard = new ChessBoard();

        // when
        Map<Position, Piece> setResult = chessBoard.getChessBoard();

        // then
        assertThat(setResult).hasSize(32);
    }
}
