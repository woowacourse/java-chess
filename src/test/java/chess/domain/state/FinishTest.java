package chess.domain.state;

import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class FinishTest {

//    @DisplayName("결과 반환")
//    @ParameterizedTest
//    @CsvSource(value = {"WHITE,BLACK", "BLACK,WHITE"})
//    void statusWhiteWin(final Color winColor, final Color loseColor) {
//        final Board board = new Board(() -> new HashMap<>(Map.of(
//                Position.from("a1"), new King(winColor),
//                Position.from("a3"), new Pawn(loseColor)
//        )));
//        final State state = new Finish(board, Color.BLACK);
//
//        assertThat(state.status()).isEqualTo(new Status(result, board));
//    }
}
