package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class EndTest {

    @DisplayName("점수 계산 기능")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,20.5", "WHITE,1"})
    void test(final Color color, final double expected) {
        final Map<Position, Piece> value = new HashMap<>(Map.of(
                Position.from("a1"), new King(Color.BLACK),
                Position.from("a2"), new Pawn(Color.BLACK),
                Position.from("a3"), new Bishop(Color.BLACK),
                Position.from("a4"), new Rook(Color.BLACK),
                Position.from("a5"), new Queen(Color.BLACK),
                Position.from("a6"), new Knight(Color.BLACK),
                Position.from("b1"), new Pawn(Color.WHITE),
                Position.from("b2"), new Pawn(Color.WHITE)
        ));
        final var state = new End(Color.BLACK, new Board(() -> value));

        assertThat(state.score(color)).isEqualTo(expected);
    }
}
