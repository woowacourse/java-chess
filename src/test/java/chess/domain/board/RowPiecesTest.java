package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.fixture.CoordinateFixture.C_EIGHT;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RowPiecesTest {
    private RowPieces rowPieces;

    @BeforeEach
    void setUp() {
        rowPieces = new RowPieces(8);
    }

    @Test
    void 한_행마다_8개의_기물을_저장한다() {
        assertThat(rowPieces.pieces()).hasSize(8);
    }

    @ParameterizedTest(name = "rowNum : {0}, expectedCompareNum : {1}")
    @CsvSource(value = {"6,1", "7,0", "8,-1"})
    void 행의_번호를_빼서_반환한다(int rowNum, int expectedCompareNum) {
        RowPieces rowPieces = new RowPieces(7);
        assertThat(rowPieces.compareTo(new RowPieces(rowNum))).isEqualTo(expectedCompareNum);
    }
    
    @ParameterizedTest(name = "rowNum : {0}, expectedResult : {1}")
    @CsvSource(value = {"8,true", "7,false", "1,false"})
    void 해당_좌표를_가진_Piece가_들어있는지_확인한다(int rowNum, boolean expectedResult) {
        boolean containsSameCoordinate = rowPieces.containsSameCoordinate(new Coordinate('a', rowNum));
        assertThat(containsSameCoordinate).isEqualTo(expectedResult);
    }
    
    @Test
    void 해당_좌표를_가지고있는_Piece_반환() {
        Piece piece = rowPieces.findPieceByCoordinate(C_EIGHT);
        assertThat(piece).isEqualTo(new Bishop(Team.WHITE, C_EIGHT));
    }
    
    @Test
    void 해당_좌표에_비어있지_않은지_확인한다() {
        boolean isNotEmpty = rowPieces.isPieceByCoordinateNotEmpty(C_EIGHT);
        assertThat(isNotEmpty).isTrue();
    }
}
