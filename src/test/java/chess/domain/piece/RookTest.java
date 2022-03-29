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

class RookTest {
    @ParameterizedTest(name = "rank 거리 : {0}, file 거리 : {1}")
    @CsvSource(value = {"1:0", "0:1", "2:0", "0:2"}, delimiter = ':')
    @DisplayName("룩 이동 테스트 - movable")
    void rookMovable(int rankDistance, int fileDistance) {
        // given & when
        Rook rook = new Rook(Color.WHITE);
        Bishop opponent = new Bishop(Color.BLACK);
        Distance distance = new Distance(rankDistance, fileDistance);

        // then
        assertThat(rook.movable(distance, opponent)).isTrue();
    }

    @ParameterizedTest(name = "rank 거리 : {0}, file 거리 : {1}")
    @CsvSource(value = {"1:3", "2:1", "2:3"}, delimiter = ':')
    @DisplayName("룩 이동 실패 테스트 - movable")
    void rookMovableFail(int rankDistance, int fileDistance) {
        // given & when
        Rook rook = new Rook(Color.WHITE);
        Bishop opponent = new Bishop(Color.BLACK);
        Distance distance = new Distance(rankDistance, fileDistance);

        // then
        assertThat(rook.movable(distance, opponent)).isFalse();
    }


    @ParameterizedTest(name = "출발지 : F5, 도착지 : {0}")
    @ValueSource(strings = {"F1", "F8", "A5", "H5"})
    @DisplayName("룩 이동 테스트")
    void rookMove(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        testBoard.put(Position.of("f5"), new Rook(Color.WHITE));
        Board board = BoardFactory.newInstance(testBoard);

        // when
        MoveResult result = board.move("f5", to);

        // then
        assertThat(result).isEqualTo(MoveResult.SUCCESS);
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
        MoveResult result = board.move("f5", to);

        // then
        assertThat(result).isEqualTo(MoveResult.FAIL);
    }

    @ParameterizedTest(name = "출발지 : E4, 도착지 : {0}")
    @ValueSource(strings = {"E8", "E1", "A4", "H4"})
    @DisplayName("타 기물로 인한 룩 이동 실패 테스트")
    void rookMoveFailCausedByPieceOnTheWay(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        Stream.of("E6", "E2", "B4", "G4")
                .forEach(position -> testBoard.put(Position.of(position), new Pawn(Color.BLACK)));

        testBoard.put(Position.of("E4"), new Rook(Color.WHITE));
        Board board = BoardFactory.newInstance(testBoard);

        // when
        MoveResult result = board.move("e4", to);

        // then
        assertThat(result).isEqualTo(MoveResult.FAIL);
    }
}
