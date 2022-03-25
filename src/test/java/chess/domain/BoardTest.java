package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BoardTest {
    @ParameterizedTest(name = "from : {0} | to : {1}")
    @CsvSource(value = {"b2,b3"})
    @DisplayName("말 이동 테스트")
    void movePiece(String from, String to) {
        // given
        Board board = BoardFactory.newInstance();

        // when
        boolean result = board.move(from, to);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("점수 계산 테스트")
    void calculateScore() {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        testBoard.put(Position.of("D5"), new Rook(Color.WHITE));
        testBoard.put(Position.of("F5"), new Rook(Color.BLACK));
        Board board = BoardFactory.newInstance(testBoard);

        // when
        Map<Color, Double> score = board.getScore();

        // then
        assertAll(
                () -> assertThat(score.get(Color.BLACK)).isEqualTo(5),
                () -> assertThat(score.get(Color.WHITE)).isEqualTo(5)
        );
    }

    @Test
    @DisplayName("폰이 한 줄에 겹쳐있을 경우 각 0.5점 처리 테스트")
    void calculatePawnsOnTheSameFile() {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        testBoard.put(Position.of("D5"), new Pawn(Color.WHITE));
        testBoard.put(Position.of("D3"), new Pawn(Color.WHITE));
        testBoard.put(Position.of("D4"), new Pawn(Color.WHITE));
        testBoard.put(Position.of("F5"), new Pawn(Color.WHITE));
        testBoard.put(Position.of("F6"), new Pawn(Color.WHITE));
        Board board = BoardFactory.newInstance(testBoard);

        // when
        Map<Color, Double> score = board.getScore();

        // then
        assertThat(score.get(Color.WHITE)).isEqualTo(2.5);
    }
}
