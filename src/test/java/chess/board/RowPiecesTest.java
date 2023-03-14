package chess.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RowPiecesTest {
    
    @Test
    void 한_행마다_8개의_기물을_저장한다() {
        int rowNum = 8 ;
        RowPieces rowPieces = new RowPieces(rowNum);
    
        assertThat(rowPieces.pieces()).hasSize(8);
    }
}
