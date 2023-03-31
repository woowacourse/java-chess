package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.RowPieces;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Column;
import chess.domain.piece.coordinate.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
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

    @ParameterizedTest
    @CsvSource(value = {"8,WHITE,30","7,WHITE,8","1,BLACK,30","2,BLACK,8"})
    void 한_행의_전체_점수_합을_구한다(String rowNum, Team team, double point){
        RowPieces testRowPieces = new RowPieces(rowNum);
        assertThat(testRowPieces.sumPiecePoints(team)).isEqualTo(point);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,BLACK,false","2,BLACK,true","8,WHITE,false","7,WHITE,true"})
    void 해당_행의_특정_열에_폰이_있는지_확인한다(String rowNum,Team team,boolean expected){
        RowPieces testRowPieces = new RowPieces(rowNum);
        assertThat(testRowPieces.checkPawnByColumn(Column.A,team)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,BLACK,true","1,WHITE,false","8,WHITE,true","8,BLACK,false"})
    void 해당_행에_특정_팀의_왕이_살아있는지_확인한다(String rowNum,Team team,boolean expected){
        RowPieces testRowPieces = new RowPieces(rowNum);
        assertThat(testRowPieces.isContainsKing(team)).isEqualTo(expected);
    }

}
