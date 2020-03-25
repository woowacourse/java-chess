package chess.board.piece;

import chess.board.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    @DisplayName("폰 움직일 수 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"1,1,WHITE,true", "0,-1,WHITE,false", "0,-1,BLACK,true", "2,-1,BLACK,false"})
    void canMove(int fileVariation, int rankVariation, Team team, boolean expect) {
        Pawn pawn = new Pawn(team);
        assertThat(pawn.canMove(new Vector(fileVariation, rankVariation))).isEqualTo(expect);
    }
}