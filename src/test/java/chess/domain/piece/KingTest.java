package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import chess.domain.MoveResult;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class KingTest {
    @ParameterizedTest(name = "출발지 : D5, 도착지 : {0}")
    @ValueSource(strings = {"D6", "E6", "E5", "E4", "D4", "C4", "C5", "C6"})
    @DisplayName("킹 이동 테스트")
    void rookMove(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        testBoard.put(Position.of("D5"), new King(Color.WHITE));
        Board board = BoardFactory.newInstance(testBoard);

        // when
        MoveResult move = board.move("D5", to);

        // then
        assertThat(move).isEqualTo(MoveResult.SUCCESS);
    }

    @ParameterizedTest(name = "출발지 : D5, 도착지 : {0}")
    @ValueSource(strings = {"D6", "E6", "E5", "E4", "D4", "C4", "C5", "C6"})
    @DisplayName("아군 기물로 인한 킹 이동 실패 테스트")
    void rookMoveFailCausedBySameColoredPiece(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        Stream.of("D6", "E6", "E5", "E4", "D4", "C4", "C5", "C6")
                .forEach(position -> testBoard.put(Position.of(position), new Pawn(Color.WHITE)));

        testBoard.put(Position.of("D5"), new King(Color.WHITE));
        Board board = BoardFactory.newInstance(testBoard);

        // when
        MoveResult move = board.move("D5", to);

        // then
        assertThat(move).isEqualTo(MoveResult.FAIL);
    }
}
