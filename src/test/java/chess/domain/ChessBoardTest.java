package chess.domain;

import chess.domain.chessPiece.ChessPiece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardTest {

    ChessBoard chessBoard = new ChessBoard();

    @Test
    @DisplayName("체스판을 초기화하고 기물을 배치한다.")
    void init() {
        int actual = chessBoard.countPiece();
        int expected = 32;
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"a1:r", "a8:R"}, delimiter = ':')
    @DisplayName("위치를 기반으로 기물을 찾는다.")
    void findPiece(String position, String expected) {
        Optional<ChessPiece> possiblePiece = chessBoard.findPiece(position);
        String actual = possiblePiece.get().getName();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("위치에 기물이 있는지 확인한다.")
    void findPiece_Null(){
        Optional<ChessPiece> actual = chessBoard.findPiece("a3");
        assertThat(actual.isEmpty()).isEqualTo(true);
    }
}