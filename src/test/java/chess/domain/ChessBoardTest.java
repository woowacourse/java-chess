package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ChessBoardTest {

    @Test
    @DisplayName("생성 시 null 예외발생")
    void constructorNullException() {
        assertThatThrownBy(() -> new ChessBoard(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("pieces는 null이 들어올 수 없습니다.");
    }

    @Test
    @DisplayName("체스판 생성")
    void createNewChessBoard() {
        ChessBoard chessBoard = ChessBoard.createNewChessBoard();
        assertThat(chessBoard.getPieces()).hasSize(32);
    }
}
