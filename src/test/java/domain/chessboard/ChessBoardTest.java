package domain.chessboard;

import domain.piece.*;
import domain.squarestatus.SquareStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성한다.")
    void givenChessBoard_thenSize64() {
        final int sum = ChessBoardFactory.generate()
                .getChessBoard()
                .values().size();

        assertThat(sum).isEqualTo(32);
    }

    @Test
    @DisplayName("색깔을 입력하면 Pawn을 제외한 해당 색깔의 모든 기물을 리스트로 반환한다,")
    void givenColor_thenReturnPieceList() {
        //given
        final ChessBoard chessBoard = ChessBoardFactory.generate();

        //when
        final List<SquareStatus> whitePieces = chessBoard.findPieces(Color.WHITE);

        //then
        assertThat(whitePieces).hasSize(8);
    }

    @Test
    @DisplayName("색깔을 입력하면 해당 색깔의 Pawn 개수를 칼럼별로 리스트화한다.")
    void givenColor_thenPawnCountList() {
        //given
        final ChessBoard chessBoard = ChessBoardFactory.generate();

        //when
        final List<Long> columnPawnCount = chessBoard.findColumnPawnCounts(Color.WHITE);


        //then
        assertThat(columnPawnCount)
                .containsOnly(1L)
                .hasSize(8);
    }
}
