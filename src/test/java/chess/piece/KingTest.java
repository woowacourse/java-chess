package chess.piece;

import chess.piece.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class KingTest {
    @Test
    void King은_자신의_심볼을_반환한다() {
        Piece king = new King(Team.WHITE, new Coordinate(1, 'a'));
        assertThat(king.symbol()).isEqualTo('k');
    }
    
    @ParameterizedTest
    @CsvSource(value = {"2,b,true","3,b,false"})
    void 도착지를_제시하고_이동할_수_있는지_판단한다(int targetRow, char targetColumn, boolean expectedResult) {
        Piece king = new King(Team.WHITE, new Coordinate(1, 'a'));
        Piece targetPiece = new Empty(Team.EMPTY, new Coordinate(targetRow,targetColumn));
        assertThat(king.isMoveable(targetPiece)).isEqualTo(expectedResult);
    }
}
