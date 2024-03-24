package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("보드 생성 시 32개의 기물이 초기화된다.")
    void printMap() {
        Board board = new Board();

        Map<Position, Piece> boardMap = board.getBoard();

        assertThat(boardMap).hasSize(32);
    }
}
