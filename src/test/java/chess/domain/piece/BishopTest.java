package chess.domain.piece;

import chess.domain.coordinate.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @DisplayName("비숍은 대각선으로 무한정 움직일 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"3,-3,true", "3,-2,false"})
    void canMove(int fileVariation, int rankVariation, boolean expect) {
        Bishop bishop = new Bishop(Team.BLACK);
        assertThat(bishop.canMove(new Vector(fileVariation, rankVariation), new Blank())).isEqualTo(expect);
    }
}