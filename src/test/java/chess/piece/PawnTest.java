package chess.piece;

import chess.piece.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PawnTest {
    @Test
    void Pawn은_자신의_심볼을_반환한다() {
        Piece pawn = new Pawn(Team.WHITE, new Coordinate(1, 'a'));
        assertThat(pawn.symbol()).isEqualTo('p');
    }
    
    @ParameterizedTest(name = "team : {0}, targetRow : {1}, targetColumn : {2}, expectedResult : {3}")
    @CsvSource(value = {"WHITE,2,a,true", "BLACK,2,a,false", "WHITE,4,a,false", "BLACK,4,a,true", "WHITE,3,b,false", "BLACK,3,b,false", "WHITE,3,a,false"})
    void 첫_시작이_아닌_경우_도착지를_제시하고_태생적으로_이동할_수_있는지_판단한다(Team team, int targetRow, char targetColumn, boolean expectedResult) {
        Piece pawn = new Pawn(team, new Coordinate(3, 'a'));
        Piece targetPiece = new Empty(Team.EMPTY, new Coordinate(targetRow,targetColumn));
        assertThat(pawn.isMovable(targetPiece)).isEqualTo(expectedResult);
    }
    
    @ParameterizedTest(name = "targetTeam : {0}, expectedResult : {1}")
    @CsvSource(value = {"WHITE,false","EMPTY,true","BLACK,true"})
    void 첫_시작이_아닌_경우_도착지에_같은_팀이_있으면_이동할_수_없다(Team targetTeam, boolean expectedResult) {
        Piece pawn = new Pawn(Team.WHITE, new Coordinate(3, 'a'));
        Piece targetPiece = new Queen(targetTeam, new Coordinate(2, 'a'));
        assertThat(pawn.isMovable(targetPiece)).isEqualTo(expectedResult);
    }
}
