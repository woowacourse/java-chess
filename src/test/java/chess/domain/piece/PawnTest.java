package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {
    @ParameterizedTest(name = "출발지 : {0}, 도착지 : {1}")
    @CsvSource(value = {"B2, B3"})
    @DisplayName("Pawn 앞으로 전진 테스트")
    void pawnMoveForward(String from, String to) {
        // given
        Board board = new Board();

        // when
        boolean move = board.move(from, to);

        // then
        assertThat(move).isTrue();
    }
}
