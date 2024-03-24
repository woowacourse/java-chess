package view.dto;

import static org.assertj.core.api.Assertions.assertThat;

import domain.board.Board;
import domain.board.BoardInitiator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DtoMapperTest {
    @Test
    @DisplayName("DtoMapper는 체스 보드의 한 랭크 정보를 반환한다.")
    void getRankInfo() {
        Board board = new Board(BoardInitiator.init());
        int rank = 0;

        RankInfo rankInfo = DtoMapper.getPieceShapeOn(board, rank);

        assertThat(rankInfo.getPieces()).containsExactly("r", "n", "b", "q", "k", "b", "n", "r");
    }
}
