package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import chess.domain.Distance;
import chess.domain.board.MoveResult;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BishopTest {
    @ParameterizedTest(name = "rank 거리 : {0}, file 거리 : {1}")
    @CsvSource(value = {"1:1", "2:2", "3:3"}, delimiter = ':')
    @DisplayName("비숍 이동 테스트 - movable")
    void bishopMovable(int rankDistance, int fileDistance) {
        // given & when
        Bishop bishop = new Bishop(Color.WHITE);
        Bishop opponent = new Bishop(Color.BLACK);
        Distance distance = new Distance(rankDistance, fileDistance);

        // then
        assertThat(bishop.movable(distance, opponent)).isTrue();
    }

    @ParameterizedTest(name = "rank 거리 : {0}, file 거리 : {1}")
    @CsvSource(value = {"1:0", "1:2", "4:3"}, delimiter = ':')
    @DisplayName("비숍 이동 실패 테스트 - movable")
    void bishopMovableFail(int rankDistance, int fileDistance) {
        // given & when
        Bishop bishop = new Bishop(Color.WHITE);
        Bishop opponent = new Bishop(Color.BLACK);
        Distance distance = new Distance(rankDistance, fileDistance);

        // then
        assertThat(bishop.movable(distance, opponent)).isFalse();
    }

    @ParameterizedTest(name = "출발지 : E4, 도착지 : {0}")
    @ValueSource(strings = {"A8", "B1", "H1", "H7"})
    @DisplayName("비숍 이동 테스트 - board 전체")
    void bishopMove(String to) {
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
