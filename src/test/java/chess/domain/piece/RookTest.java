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
