package chess.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RowPiecesTest {
    private RowPieces rowPieces;
    
    @BeforeEach
    void setUp() {
        rowPieces = new RowPieces(7);
    }
    
    @Test
    void 한_행마다_8개의_기물을_저장한다() {
        assertThat(rowPieces.pieces()).hasSize(8);
    }
    
    @ParameterizedTest(name = "rowNum : {0}, expectedCompareNum : {1}")
    @CsvSource(value = {"6,1", "7,0", "8,-1"})
    void 행의_번호를_빼서_반환한다(int rowNum, int expectedCompareNum) {
        assertThat(rowPieces.compareTo(new RowPieces(rowNum))).isEqualTo(expectedCompareNum);
    }
}
