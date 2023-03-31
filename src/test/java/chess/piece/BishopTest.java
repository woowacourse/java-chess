package chess.piece;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.view.SymbolMatcher;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BishopTest {

    @Test
    void Bishop은_자신의_심볼을_반환한다() {
        Piece bishop = new Bishop(Team.WHITE, Coordinate.createCoordinate("1", "a"));
        assertThat(bishop.symbol()).isEqualTo(SymbolMatcher.BISHOP);
    }

    @ParameterizedTest(name = "targetRow : {0}, targetColumn : {1}, expectedResult : {2}")
    @CsvSource(value = {"3,c,true", "3,b,false", "3,a,false", "1,a,false"})
    void 도착지를_제시하고_태생적으로_이동할_수_있는지_판단한다(String targetRow, String targetColumn, boolean expectedResult) {
        Piece bishop = new Bishop(Team.WHITE, Coordinate.createCoordinate("1", "a"));
        Piece targetPiece = new Empty(Team.EMPTY, Coordinate.createCoordinate(targetRow, targetColumn));
        assertThat(bishop.isMovable(targetPiece)).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "targetTeam : {0}, expectedResult : {1}")
    @CsvSource(value = {"WHITE,false", "EMPTY,true", "BLACK,true"})
    void 도착지에_같은_팀이_있으면_이동할_수_없다(Team targetTeam, boolean expectedResult) {
        Piece bishop = new Bishop(Team.WHITE, Coordinate.createCoordinate("1", "a"));
        Piece targetPiece = new Queen(targetTeam, Coordinate.createCoordinate("2", "b"));
        assertThat(bishop.isMovable(targetPiece)).isEqualTo(expectedResult);
    }
}
