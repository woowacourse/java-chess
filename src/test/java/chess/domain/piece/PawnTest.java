package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import chess.domain.MoveResult;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {
    @ParameterizedTest(name = "출발지 : {0}, 도착지 : {1}")
    @CsvSource(value = {"B2, B3"})
    @DisplayName("Pawn 앞으로 전진 테스트")
    void pawnMoveForward(String from, String to) {
        // given
        Board board = BoardFactory.newInstance();

        // when
        MoveResult move = board.move(from, to);

        // then
        assertThat(move).isEqualTo(MoveResult.SUCCESS);
    }

    @Test
    @DisplayName("아군 기물 존재로 인한 폰 이동 실패 테스트")
    void pawnMoveShouldFailCausedBySameColoredPiece() {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        testBoard.put(Position.of("F6"), new Rook(Color.WHITE));
        testBoard.put(Position.of("F5"), new Pawn(Color.WHITE));
        Board board = BoardFactory.newInstance(testBoard);

        // when
        MoveResult move = board.move("F5", "F6");

        // then
        assertThat(move).isEqualTo(MoveResult.FAIL);
    }
}
