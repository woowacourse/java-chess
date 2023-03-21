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
class RookTest {
    @Test
    void Rook은_자신의_pieceType을_반환한다() {
        Piece rook = new Rook(Team.WHITE, A_ONE);
        assertThat(PieceSymbolConverter.convert(rook.pieceType())).isEqualTo("r");
    }
    
    @ParameterizedTest(name = "targetRow : {0}, targetColumn : {1}, expectedResult : {2}")
    @CsvSource(value = {"3,a,true", "1,c,true", "3,c,false", "3,b,false", "1,a,false"})
    void 도착지를_제시하고_태생적으로_이동할_수_있는지_판단한다(int targetRow, char targetColumn, boolean expectedResult) {
        Piece rook = new Rook(Team.WHITE, A_ONE);
        Piece targetPiece = new Empty(Team.EMPTY, new Coordinate(targetColumn, targetRow));
        assertThat(rook.isMovable(targetPiece)).isEqualTo(expectedResult);
    }
    
    @ParameterizedTest(name = "targetTeam : {0}, expectedResult : {1}")
    @CsvSource(value = {"WHITE,false","EMPTY,true","BLACK,true"})
    void 도착지에_같은_팀이_있으면_이동할_수_없다(Team targetTeam, boolean expectedResult) {
        Piece rook = new Rook(Team.WHITE, A_ONE);
        Piece targetPiece = new Queen(targetTeam, new Coordinate('a', 2));
        assertThat(rook.isMovable(targetPiece)).isEqualTo(expectedResult);
    }
}
