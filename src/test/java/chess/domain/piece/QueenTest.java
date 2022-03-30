package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import chess.domain.Distance;
import chess.domain.board.MoveResult;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.TestBoardFactory;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class QueenTest {
    @ParameterizedTest(name = "rank 거리 : {0}, file 거리 : {1}")
    @CsvSource(value = {"1:1", "0:1", "1:0", "2:2"}, delimiter = ':')
    @DisplayName("퀸 이동 테스트 - movable")
    void queenMovable(int rankDistance, int fileDistance) {
        // given & when
        Queen queen = new Queen(Color.WHITE);
        Bishop opponent = new Bishop(Color.BLACK);
        Distance distance = new Distance(rankDistance, fileDistance);

        // then
        assertThat(queen.movable(distance, opponent)).isTrue();
    }

    @ParameterizedTest(name = "rank 거리 : {0}, file 거리 : {1}")
    @CsvSource(value = {"1:3", "2:1", "2:3"}, delimiter = ':')
    @DisplayName("퀸 이동 실패 테스트 - movable")
    void queenMovableFail(int rankDistance, int fileDistance) {
        // given & when
        Queen queen = new Queen(Color.WHITE);
        Bishop opponent = new Bishop(Color.BLACK);
        Distance distance = new Distance(rankDistance, fileDistance);

        // then
        assertThat(queen.movable(distance, opponent)).isFalse();
    }

    @ParameterizedTest(name = "출발지 : D5, 도착지 : {0}")
    @ValueSource(strings = {"A8", "A5", "A2", "D1", "H1", "H5", "G8", "D6"})
    @DisplayName("퀸 이동 테스트")
    void queenMove(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        testBoard.put(Position.of("D5"), new Queen(Color.WHITE));
        TestBoardFactory testBoardFactory = new TestBoardFactory(testBoard);
        Board board = testBoardFactory.createBoard();

        // when
        MoveResult result = board.move("d5", to);

        // then
        assertThat(result).isEqualTo(MoveResult.SUCCESS);
    }

    @ParameterizedTest(name = "출발지 : D5, 도착지 : {0}")
    @ValueSource(strings = {"D8", "F7", "G5", "G2", "D4", "C4", "C5", "C6"})
    @DisplayName("아군 기물로 인한 퀸 이동 실패 테스트")
    void queenMoveFailCausedBySameColoredPiece(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        Stream.of("D8", "F7", "G5", "G2", "D4", "C4", "C5", "C6")
                .forEach(position -> testBoard.put(Position.of(position), new Pawn(Color.WHITE)));

        testBoard.put(Position.of("D5"), new Queen(Color.WHITE));
        TestBoardFactory testBoardFactory = new TestBoardFactory(testBoard);
        Board board = testBoardFactory.createBoard();

        // when
        MoveResult result = board.move("d5", to);

        // then
        assertThat(result).isEqualTo(MoveResult.FAIL);
    }

    @ParameterizedTest(name = "출발지 : D5, 도착지 : {0}")
    @ValueSource(strings = {"A8", "A5", "A2", "D1", "H1", "H5", "G8", "D8"})
    @DisplayName("타 기물로 인한 퀸 이동 실패 테스트")
    void queenMoveFailCausedByPieceOnTheWay(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        Stream.of("D6", "C5", "E5", "C4", "D2", "F3", "G5", "D7")
                .forEach(position -> testBoard.put(Position.of(position), new Pawn(Color.BLACK)));

        testBoard.put(Position.of("D5"), new Rook(Color.WHITE));
        TestBoardFactory testBoardFactory = new TestBoardFactory(testBoard);
        Board board = testBoardFactory.createBoard();

        // when
        MoveResult result = board.move("d5", to);

        // then
        assertThat(result).isEqualTo(MoveResult.FAIL);
    }
}
