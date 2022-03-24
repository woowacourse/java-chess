package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RookTest {
    @ParameterizedTest(name = "출발지 : F5, 도착지 : {0}")
    @ValueSource(strings = {"F1", "F8", "A5", "H5"})
    @DisplayName("룩 이동 테스트")
    void rookMove(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        testBoard.put(Position.of("f5"), new Rook(Color.WHITE));
        Board board = BoardFactory.newInstance(testBoard);

        // when
        boolean move = board.move("f5", to);

        // then
        assertThat(move).isTrue();
    }

    @ParameterizedTest(name = "출발지 : F5, 도착지 : {0}")
    @ValueSource(strings = {"F1", "F8", "A5", "H5"})
    @DisplayName("아군 기물로 인한 룩 이동 실패 테스트")
    void rookMoveFailCausedBySameColoredPiece(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        Stream.of("F1", "F8", "A5", "H5")
                .forEach(position -> testBoard.put(Position.of(position), new Pawn(Color.WHITE)));

        testBoard.put(Position.of("F5"), new Rook(Color.WHITE));
        Board board = BoardFactory.newInstance(testBoard);

        // when
        boolean move = board.move("F5", to);

        // then
        assertThat(move).isFalse();
    }
}
