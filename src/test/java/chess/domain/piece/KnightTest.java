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

class KnightTest {
    @ParameterizedTest(name = "rank 거리 : {0}, file 거리 : {1}")
    @CsvSource(value = {"1:2", "2:1"}, delimiter = ':')
    @DisplayName("나이트 이동 테스트 - movable")
    void knightMovable(int rankDistance, int fileDistance) {
        // given & when
        Knight knight = new Knight(Color.WHITE);
        Bishop opponent = new Bishop(Color.BLACK);
        Distance distance = new Distance(rankDistance, fileDistance);

        // then
        assertThat(knight.movable(distance, opponent)).isTrue();
    }

    @ParameterizedTest(name = "rank 거리 : {0}, file 거리 : {1}")
    @CsvSource(value = {"1:3", "2:0", "2:2"}, delimiter = ':')
    @DisplayName("나이트 이동 실패 테스트 - movable")
    void knightMovableFail(int rankDistance, int fileDistance) {
        // given & when
        Knight knight = new Knight(Color.WHITE);
        Bishop opponent = new Bishop(Color.BLACK);
        Distance distance = new Distance(rankDistance, fileDistance);

        // then
        assertThat(knight.movable(distance, opponent)).isFalse();
    }

    @ParameterizedTest(name = "출발지 : F5, 도착지 : {0}")
    @ValueSource(strings = {"D6", "E7", "G7", "H6", "H4", "G3", "E3", "D4"})
    @DisplayName("나이트 이동 테스트")
    void knightMove(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        testBoard.put(Position.of("F5"), new Knight(Color.WHITE));
        TestBoardFactory testBoardFactory = new TestBoardFactory(testBoard);
        Board board = testBoardFactory.createBoard();

        // when
        MoveResult result = board.move("F5", to);

        // then
        assertThat(result).isEqualTo(MoveResult.SUCCESS);
    }

    @ParameterizedTest(name = "출발지 : F5, 도착지 : {0}")
    @ValueSource(strings = {"D6", "E7", "G7", "H6", "H4", "G3", "E3", "D4"})
    @DisplayName("아군 기물로 인한 나이트 이동 실패 테스트")
    void knightMoveFailCausedBySameColoredPiece(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        Stream.of("D6", "E7", "G7", "H6", "H4", "G3", "E3", "D4")
                .forEach(position -> testBoard.put(Position.of(position), new Pawn(Color.WHITE)));

        testBoard.put(Position.of("F5"), new Knight(Color.WHITE));
        TestBoardFactory testBoardFactory = new TestBoardFactory(testBoard);
        Board board = testBoardFactory.createBoard();

        // when
        MoveResult result = board.move("F5", to);

        // then
        assertThat(result).isEqualTo(MoveResult.FAIL);
    }

    @ParameterizedTest(name = "출발지 : F5, 도착지 : {0}")
    @ValueSource(strings = {"D6", "E7", "G7", "H6", "H4", "G3", "E3", "D4"})
    @DisplayName("타 기물이 이동 경로에 있어도 나이트는 이동 가능하다")
    void knightMoveThroughOpponent(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        Stream.of("E5", "F6", "F4", "G5")
                .forEach(position -> testBoard.put(Position.of(position), new Pawn(Color.BLACK)));

        testBoard.put(Position.of("F5"), new Knight(Color.WHITE));
        TestBoardFactory testBoardFactory = new TestBoardFactory(testBoard);
        Board board = testBoardFactory.createBoard();

        // when
        MoveResult result = board.move("F5", to);

        // then
        assertThat(result).isEqualTo(MoveResult.SUCCESS);
    }
}
