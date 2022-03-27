package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    @DisplayName("게임 승패 결과 반환 테스트")
    @ParameterizedTest
    @CsvSource(value = {"WHITE, WHITE_WIN", "BLACK, BLACK_WIN"})
    void gameResult(final Color color, final Result expected) {
        final Board board = new Board(() -> Map.of(Position.from("a1"), new King(color)));

        assertThat(Result.from(board)).isEqualTo(expected);
    }
}
