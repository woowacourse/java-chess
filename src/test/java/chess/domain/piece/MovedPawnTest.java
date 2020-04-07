package chess.domain.piece;

import chess.domain.coordinate.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class MovedPawnTest {
    @DisplayName("폰 움직일 수 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"1,1,WHITE,false", "0,-1,WHITE,false", "0,-1,BLACK,true", "2,-1,BLACK,false", "0,2,WHITE,false"})
    void canMove(int fileVariation, int rankVariation, Team team, boolean expect) {
        Piece piece = new MovedPawn(team);
        assertThat(piece.canMove(new Vector(fileVariation, rankVariation), new Blank())).isEqualTo(expect);
    }
}