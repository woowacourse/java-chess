package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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