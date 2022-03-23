package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BoardTest {

    @ParameterizedTest
    @CsvSource(value = {"a1:Rook", "b1:Knight", "c1:Bishop", "d1:Queen", "e1:King", "a2:Pawn", "a3:Blank"}, delimiter = ':')
    void findPiece(String position, String symbol) {
        Board board = new Board();
        Assertions.assertThat(board.getPiece(position).getClass().getSimpleName()).isEqualTo(symbol);
    }
}
