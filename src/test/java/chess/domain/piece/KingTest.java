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

class KingTest {
    @ParameterizedTest(name = "rank 거리 : {0}, file 거리 : {1}")
    @CsvSource(value = {"1:0", "1:1", "0:1"}, delimiter = ':')
    @DisplayName("킹 이동 테스트 - movable")
    void kingMovable(int rankDistance, int fileDistance) {
        // given & when
        King king = new King(Color.WHITE);
        Bishop opponent = new Bishop(Color.BLACK);
        Distance distance = new Distance(rankDistance, fileDistance);

        // then
        assertThat(king.movable(distance, opponent)).isTrue();
    }

    @ParameterizedTest(name = "rank 거리 : {0}, file 거리 : {1}")
    @CsvSource(value = {"1:2", "2:0", "2:2"}, delimiter = ':')
    @DisplayName("킹 이동 실패 테스트 - movable")
    void kingMovableFail(int rankDistance, int fileDistance) {
        // given & when
        King king = new King(Color.WHITE);
        Bishop opponent = new Bishop(Color.BLACK);
        Distance distance = new Distance(rankDistance, fileDistance);

        // then
        assertThat(king.movable(distance, opponent)).isFalse();
    }

    @ParameterizedTest(name = "출발지 : D5, 도착지 : {0}")
    @ValueSource(strings = {"D6", "E6", "E5", "E4", "D4", "C4", "C5", "C6"})
    @DisplayName("킹 이동 테스트")
    void kingMove(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        testBoard.put(Position.of("D5"), new King(Color.WHITE));
        Board board = BoardFactory.newInstance(testBoard);

        // when
        MoveResult result = board.move("D5", to);

        // then
        assertThat(result).isEqualTo(MoveResult.SUCCESS);
    }

    @ParameterizedTest(name = "출발지 : D5, 도착지 : {0}")
    @ValueSource(strings = {"D6", "E6", "E5", "E4", "D4", "C4", "C5", "C6"})
    @DisplayName("아군 기물로 인한 킹 이동 실패 테스트")
    void kingMoveFailCausedBySameColoredPiece(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        Stream.of("D6", "E6", "E5", "E4", "D4", "C4", "C5", "C6")
                .forEach(position -> testBoard.put(Position.of(position), new Pawn(Color.WHITE)));

        testBoard.put(Position.of("D5"), new King(Color.WHITE));
        Board board = BoardFactory.newInstance(testBoard);

        // when
        MoveResult result = board.move("D5", to);

        // then
        assertThat(result).isEqualTo(MoveResult.FAIL);
    }
}
