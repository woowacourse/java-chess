package chess.domain.piece;

import chess.domain.piece.coordinate.Coordinate;
import chess.view.PieceSymbolConverter;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.fixture.CoordinateFixture.A_ONE;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PawnTest {
    @Test
    void Pawn은_자신의_pieceType을_반환한다() {
        Piece pawn = new Pawn(Team.WHITE, A_ONE);
        assertThat(PieceSymbolConverter.convert(pawn.pieceType())).isEqualTo("p");
    }
    
    @ParameterizedTest(name = "team : {0}, targetRow : {1}, targetColumn : {2}, expectedResult : {3}")
    @CsvSource(value = {"WHITE,2,a,true", "BLACK,2,a,false", "WHITE,4,a,false", "BLACK,4,a,true", "WHITE,3,b,false", "BLACK,3,b,false", "WHITE,3,a,false"})
    void 첫_시작이_아닌_경우_도착지를_제시하고_태생적으로_이동할_수_있는지_판단한다(Team team, int targetRow, char targetColumn, boolean expectedResult) {
        Piece pawn = new Pawn(team, new Coordinate('a', 3));
        Piece targetPiece = new Empty(Team.EMPTY, new Coordinate(targetColumn, targetRow));
        assertThat(pawn.isMovable(targetPiece)).isEqualTo(expectedResult);
    }
    
    @ParameterizedTest(name = "targetTeam : {0}, expectedResult : {1}")
    @CsvSource(value = {"WHITE,false", "EMPTY,true", "BLACK,false"})
    void 첫_시작이_아닌_경우_도착지에_같은_팀이_있으면_이동할_수_없다(Team targetTeam, boolean expectedResult) {
        Piece pawn = new Pawn(Team.WHITE, new Coordinate('a', 3));
        Piece targetPiece = new Queen(targetTeam, new Coordinate('a', 2));
        assertThat(pawn.isMovable(targetPiece)).isEqualTo(expectedResult);
    }
    
    @ParameterizedTest(name = "row : {0}, targetRow : {1}, targetColumn : {2}, expectedResult : {3}")
    @CsvSource(value = {"7,5,a,true", "7,5,c,false", "7,4,a,false"})
    void 화이트폰은_첫_이동하면서_도착지가_비어있으면_직선으로_두_칸_이동_가능하다(int row, int targetRow, char targetColumn, boolean expectedResult) {
        Piece pawn = new Pawn(Team.WHITE, new Coordinate('a', row));
        Piece targetPiece = new Empty(Team.EMPTY, new Coordinate(targetColumn, targetRow));
        
        assertThat(pawn.isMovable(targetPiece)).isEqualTo(expectedResult);
    }
    
    @ParameterizedTest(name = "row : {0}, targetRow : {1}, targetColumn : {2}, expectedResult : {3}")
    @CsvSource(value = {"2,4,a,true", "2,4,c,false", "2,5,a,false"})
    void 블랙폰은_첫_이동하면서_도착지가_비어있으면_직선으로_두_칸_이동_가능하다(int row, int targetRow, char targetColumn, boolean expectedResult) {
        Piece pawn = new Pawn(Team.BLACK, new Coordinate('a', row));
        Piece targetPiece = new Empty(Team.EMPTY, new Coordinate(targetColumn, targetRow));
        
        assertThat(pawn.isMovable(targetPiece)).isEqualTo(expectedResult);
    }
    
    @ParameterizedTest(name = "team : {0}, row : {1}, targetRow : {2}")
    @CsvSource(value = {"WHITE,7,5", "BLACK,2,4"})
    void 폰은_첫_이동일_때_도착지에_기물이_있으면_못간다(Team team, int row, int targetRow) {
        Piece pawn = new Pawn(team, new Coordinate('a', row));
        Piece targetPiece = new Queen(Team.BLACK, new Coordinate('a', targetRow));
        
        assertThat(pawn.isMovable(targetPiece)).isFalse();
    }
    
    @ParameterizedTest(name = "team : {0}, row : {1}, targetRow : {2}")
    @CsvSource(value = {"WHITE,2,1", "BLACK,3,4"})
    void 한_칸_앞으로_이동할_때_기물이_없으면_이동_가능하다(Team team, int row, int targetRow) {
        Piece pawn = new Pawn(team, new Coordinate('a', row));
        Piece targetPiece = new Empty(Team.EMPTY, new Coordinate('a', targetRow));
        assertThat(pawn.isMovable(targetPiece)).isTrue();
    }
    
    @ParameterizedTest(name = "team : {0}, row : {1}, targetRow : {2}")
    @CsvSource(value = {"WHITE,2,1", "BLACK,3,4"})
    void 한_칸_앞으로_이동할_때_기물이_있으면_이동_불가능하다(Team team, int row, int targetRow) {
        Piece pawn = new Pawn(team, new Coordinate('a', row));
        Piece targetPiece = new Queen(Team.BLACK, new Coordinate('a', targetRow));
        assertThat(pawn.isMovable(targetPiece)).isFalse();
    }
    
    @ParameterizedTest(name = "team : {0}, otherTeam : {1}, row : {2}, targetRow : {3}, targetColumn : {4}")
    @CsvSource(value = {"WHITE,BLACK,2,1,a", "WHITE,BLACK,2,1,c", "BLACK,WHITE,3,4,a", "BLACK,WHITE,3,4,c"})
    void 대각선_한_칸_앞으로_이동할_때_상대팀이면_이동_가능하다(Team team, Team otherTeam, int row, int targetRow, char targetColumn) {
        Piece pawn = new Pawn(team, new Coordinate('b', row));
        Piece targetPiece = new Queen(otherTeam, new Coordinate(targetColumn, targetRow));
        assertThat(pawn.isMovable(targetPiece)).isTrue();
    }
    
    @ParameterizedTest(name = "team : {0}, otherTeam : {1}, row : {2}, targetRow : {3}, targetColumn : {4}")
    @CsvSource(value = {"WHITE,WHITE,2,1,a", "WHITE,EMPTY,2,1,a", "WHITE,WHITE,2,1,c", "WHITE,EMPTY,2,1,c", "BLACK,BLACK,3,4,a", "BLACK,EMPTY,3,4,a", "BLACK,BLACK,3,4,c", "BLACK,EMPTY,3,4,c"})
    void 대각선_한_칸_앞으로_이동할_때_상대팀이_없으면_이동_불가능하다(Team team, Team otherTeam, int row, int targetRow, char targetColumn) {
        Piece pawn = new Pawn(team, new Coordinate('b', row));
        Piece targetPiece = new Queen(otherTeam, new Coordinate(targetColumn, targetRow));
        assertThat(pawn.isMovable(targetPiece)).isFalse();
    }
}
