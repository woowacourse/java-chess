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

class KnightTest {
    @ParameterizedTest(name = "출발지 : F5, 도착지 : {0}")
    @ValueSource(strings = {"D6", "E7", "G7", "H6", "H4", "G3", "E3", "D4"})
    @DisplayName("나이트 이동 테스트")
    void rookMove(String to) {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        testBoard.put(Position.of("F5"), new Knight(Color.WHITE));
        Board board = BoardFactory.newInstance(testBoard);

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
        Board board = BoardFactory.newInstance(testBoard);

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
        Board board = BoardFactory.newInstance(testBoard);

        // when
        // when
        MoveResult result = board.move("F5", to);

        // then
        assertThat(result).isEqualTo(MoveResult.SUCCESS);
    }
}
