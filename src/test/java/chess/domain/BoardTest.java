package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BoardTest {

    @ParameterizedTest(name = "from : {0} | to : {1}")
    @CsvSource(value = {"b2,b3"})
    @DisplayName("말 이동 테스트")
    void movePiece(String from, String to) {
        Board board = new Board();

        boolean result = board.move(from, to);

        assertThat(result).isTrue();
    }

}
