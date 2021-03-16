package chess;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChessBoardTest {

    @Test
    @DisplayName("체스판 생성")
    void create() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard).isEqualTo(new ChessBoard());
    }

    @ParameterizedTest
    @DisplayName("체스 기물 배치 확인")
    @CsvSource(value = {"0, 4, K", "7, 4, k", "1, 0, P", "6, 0, p", "5, 0, ."}, delimiter = ',')
    void pieceLocationCheck(int i, int j, String value) {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.getChessBoard()[i][j]).isEqualTo(value);
    }
}
