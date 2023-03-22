package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new BoardMaker());
    }

    @Test
    @DisplayName("생성된 board의 세로 길이는 8 이다.")
    void 생성된_board의_세로_길이는_8이다() {
        assertThat(board.getBoard()).hasSize(8);
    }

    @Test
    @DisplayName("생성된 board의 가로 길이는 8 이다.")
    void 생성된_board의_가로_길이는_8이다() {
        List<Rank> ranks = board.getBoard();

        assertThat(ranks.get(0).getRank()).hasSize(8);
    }

    @Test
    @DisplayName("기물을 이동한다.")
    void 기물을_이동한다() {
        List<Rank> ranks = board.getBoard();

        Position current = new Position(1, 0);
        Position target = new Position(3, 0);
        board.movePiece(current, target);

        assertAll(
                () -> assertThat(ranks.get(1).getRank().get(0).isEmptyPiece()).isTrue(),
                () -> assertThat(ranks.get(3).getRank().get(0).isEmptyPiece()).isFalse()
        );
    }
}
