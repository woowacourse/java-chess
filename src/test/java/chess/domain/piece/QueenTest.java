package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class QueenTest {
    @ParameterizedTest(name = "출발지 : D5, 도착지 : {0}")
    @ValueSource(strings = {"A8", "A5", "A2", "D1", "H1", "H5", "G8", "D8"})
    @DisplayName("퀸 이동 테스트")
    void rookMove(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        testBoard.put(Position.of("D5"), new Queen(Color.WHITE));
        Board board = BoardFactory.newInstance(testBoard);

        // when
        boolean move = board.move("D5", to);

        // then
        assertThat(move).isTrue();
    }
}
