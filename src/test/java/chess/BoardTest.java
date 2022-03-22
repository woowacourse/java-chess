package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    @DisplayName("체스판을 생성하는 테스트")
    void createBoardTest() {
        Board board = Board.create();
        assertThat(board).isExactlyInstanceOf(Board.class);
    }

    @Test
    @DisplayName("말의 위치를 확인하여 체스판 초기화 테스트")
    void initBoardTest() {
        Board board = Board.create();
        String piece = board.getPiece(new Position(File.A, Rank.EIGHT));

        assertThat(piece).isEqualTo("R");
    }
}
