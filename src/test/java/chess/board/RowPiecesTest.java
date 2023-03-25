package chess.board;

import chess.piece.coordinate.Coordinate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RowPiecesTest {

    private RowPieces rowPieces;

    @BeforeEach
    void setUp() {
        rowPieces = new RowPieces("8");
    }

    @Test
    void 한_행마다_8개의_기물을_저장한다() {
        assertThat(rowPieces.pieces()).hasSize(8);
    }

    @ParameterizedTest(name = "rowNum : {0}, expectedCompareNum : {1}")
    @CsvSource(value = {"6,1", "7,0", "8,-1"})
    void 행의_번호를_빼서_반환한다(String rowNum, int expectedCompareNum) {
        RowPieces rowPieces = new RowPieces("7");
        assertThat(rowPieces.compareTo(new RowPieces(rowNum))).isEqualTo(expectedCompareNum);
    }

    @Test
    void 기물이_해당_위치로_움직일_수_있는지_판단한다() {
        RowPieces sourceRowPieces = new RowPieces("2");
        RowPieces targetRowPieces = new RowPieces("3");
        Coordinate sourceCoordinate = Coordinate.createCoordinate("2", "a");
        Coordinate targetCoordinate = Coordinate.createCoordinate("3", "a");

        assertThat(sourceRowPieces.isMovable(targetRowPieces, sourceCoordinate, targetCoordinate)).isTrue();

    }

}
