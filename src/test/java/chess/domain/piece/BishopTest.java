package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import chess.domain.board.MoveResult;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BishopTest {
    @ParameterizedTest(name = "출발지 : E4, 도착지 : {0}")
    @ValueSource(strings = {"A8", "B1", "H1", "H7"})
    @DisplayName("비숍 이동 테스트")
    void rookMove(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        testBoard.put(Position.of("E4"), new Bishop(Color.WHITE));
        Board board = BoardFactory.newInstance(testBoard);

        // when
        MoveResult result = board.move("E4", to);

        // then
        assertThat(result).isEqualTo(MoveResult.SUCCESS);
    }

    @ParameterizedTest(name = "출발지 : E4, 도착지 : {0}")
    @ValueSource(strings = {"A8", "B1", "H1", "H7"})
    @DisplayName("아군 기물로 인한 비숍 이동 실패 테스트")
    void bishopMoveFailCausedBySameColoredPiece(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        Stream.of("A8", "B1", "H1", "H7")
                .forEach(position -> testBoard.put(Position.of(position), new Pawn(Color.WHITE)));

        testBoard.put(Position.of("E4"), new Bishop(Color.WHITE));
        Board board = BoardFactory.newInstance(testBoard);

        // when
        MoveResult result = board.move("E4", to);

        // then
        assertThat(result).isEqualTo(MoveResult.FAIL);
    }

    @ParameterizedTest(name = "출발지 : E4, 도착지 : {0}")
    @ValueSource(strings = {"A8", "B1", "H1", "H7"})
    @DisplayName("타 기물로 인한 비숍 이동 실패 테스트")
    void bishopMoveFailCausedByPieceOnTheWay(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        Stream.of("C6", "D3", "F3", "F5")
                .forEach(position -> testBoard.put(Position.of(position), new Pawn(Color.BLACK)));

        testBoard.put(Position.of("E4"), new Bishop(Color.WHITE));
        Board board = BoardFactory.newInstance(testBoard);

        // when
        MoveResult result = board.move("E4", to);

        // then
        assertThat(result).isEqualTo(MoveResult.FAIL);
    }
}
