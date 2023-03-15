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
class QueenTest {
    @Test
    void Queen은_자신의_심볼을_반환한다() {
        Piece queen = new Queen(Team.WHITE, new Coordinate(1, 'a'));
        assertThat(queen.symbol()).isEqualTo('q');
    }
    
    @ParameterizedTest(name = "targetRow : {0}, targetColumn : {1}, expectedResult : {2}")
    @CsvSource(value = {"1,c,true", "3,c,true", "2,c,false","1,a,false", "3,d,false"})
    void 도착지를_제시하고_태생적으로_이동할_수_있는지_판단한다(int targetRow, char targetColumn, boolean expectedResult) {
        Piece queen = new Queen(Team.WHITE, new Coordinate(1, 'a'));
        Piece targetPiece = new Empty(Team.EMPTY, new Coordinate(targetRow,targetColumn));
        assertThat(queen.isMovable(targetPiece)).isEqualTo(expectedResult);
    }
    
    @ParameterizedTest(name = "targetTeam : {0}, expectedResult : {1}")
    @CsvSource(value = {"WHITE,false","EMPTY,true","BLACK,true"})
    void 도착지에_같은_팀이_있으면_이동할_수_없다(Team targetTeam, boolean expectedResult) {
        Piece queen = new Queen(Team.WHITE, new Coordinate(1, 'a'));
        Piece targetPiece = new Rook(targetTeam, new Coordinate(2, 'b'));
        assertThat(queen.isMovable(targetPiece)).isEqualTo(expectedResult);
    }
}



