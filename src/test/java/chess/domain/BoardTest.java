package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BoardTest {

    @ParameterizedTest
    @CsvSource(value = {"a1:Rook", "b1:Knight", "c8:Bishop", "d1:Queen", "e1:King", "a2:Pawn", "a3:Blank"}, delimiter = ':')
    void findPiece(String position, String symbol) {
        Board board = new Board();
        Assertions.assertThat(board.getPiece(Position.from(position)).getClass().getSimpleName()).isEqualTo(symbol);
    }

    @Test
    void movePiece() {
        Board board = new Board();
        String source = "f2";
        String destination = "f4";
        board.movePiece(source, destination);
        Assertions.assertThat(board.getPiece(Position.from(destination)).isPawn()).isTrue();
        Assertions.assertThat(board.getPiece(Position.from(source)).isBlank()).isTrue();
    }
}
