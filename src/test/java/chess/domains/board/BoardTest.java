package chess.domains.board;

import chess.domains.piece.PieceColor;
import chess.domains.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    private static Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @DisplayName("점수계산 기능 확인")
    @Test
    void calculateScore() {
        board.move(Position.ofPositionName("a2"), Position.ofPositionName("a4"));
        board.move(Position.ofPositionName("b7"), Position.ofPositionName("b5"));
        board.move(Position.ofPositionName("a4"), Position.ofPositionName("b5"));

        assertThat(board.calculateScore(PieceColor.WHITE)).isEqualTo(37);
        assertThat(board.calculateScore(PieceColor.BLACK)).isEqualTo(37);
    }

    @DisplayName("입력한 위치의 말을 찾는 기능 확인")
    @ParameterizedTest
    @CsvSource(value = {"a1, ♖", "a8, ♜", "d1, ♔", "h7, ♟"})
    void findPieceByPosition(String position, String piece) {
        assertThat(board.findPieceByPosition(position)).isEqualTo(piece);
    }
}