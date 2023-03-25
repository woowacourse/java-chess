package chess.piece;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.SymbolMatcher;
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
class RookTest {

    @Test
    void Rook은_자신의_심볼을_반환한다() {
        Piece rook = new Rook(Team.WHITE, Coordinate.createCoordinate("1", "a"));
        assertThat(rook.symbol()).isEqualTo(SymbolMatcher.ROOK);
    }

    @ParameterizedTest(name = "targetRow : {0}, targetColumn : {1}, expectedResult : {2}")
    @CsvSource(value = {"3,a,true", "1,c,true", "3,c,false", "3,b,false", "1,a,false"})
    void 도착지를_제시하고_태생적으로_이동할_수_있는지_판단한다(String targetRow, String targetColumn, boolean expectedResult) {
        Piece rook = new Rook(Team.WHITE, Coordinate.createCoordinate("1", "a"));
        Piece targetPiece = new Empty(Team.EMPTY, Coordinate.createCoordinate(targetRow, targetColumn));
        assertThat(rook.isMovable(targetPiece)).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "targetTeam : {0}, expectedResult : {1}")
    @CsvSource(value = {"WHITE,false", "EMPTY,true", "BLACK,true"})
    void 도착지에_같은_팀이_있으면_이동할_수_없다(Team targetTeam, boolean expectedResult) {
        Piece rook = new Rook(Team.WHITE, Coordinate.createCoordinate("1", "a"));
        Piece targetPiece = new Queen(targetTeam, Coordinate.createCoordinate("2", "a"));
        assertThat(rook.isMovable(targetPiece)).isEqualTo(expectedResult);
    }
}
