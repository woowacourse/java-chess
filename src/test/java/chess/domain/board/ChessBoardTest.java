package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성하고, 체스판의 file과 rank의 길이가 8인지 확인한다.")
    void create() {
        // given
        final ChessBoard chessBoard = new ChessBoard();

        // when
        final List<ChessBoardRank> files = chessBoard.getFiles();

        // then
        assertThat(files.size())
                .isEqualTo(8);

        assertThat(files.get(0).getRank().size())
                .isEqualTo(8);
    }
}
